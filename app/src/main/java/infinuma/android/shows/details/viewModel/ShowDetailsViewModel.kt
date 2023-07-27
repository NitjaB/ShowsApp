package infinuma.android.shows.details.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import infinuma.android.shows.details.domain.EmailParser
import infinuma.android.shows.details.models.RatingUi
import infinuma.android.shows.details.models.ReviewUi
import infinuma.android.shows.details.models.ShowDetailsUi
import infinuma.android.shows.login.domain.UserRepository
import infinuma.android.shows.shows.data.ShowsRepository
import kotlinx.coroutines.launch

class ShowDetailsViewModel : ViewModel() {
    private lateinit var userRepository: UserRepository
    private lateinit var showsRepository: ShowsRepository

    private val _state = MutableLiveData(ShowDetailsUi())
    val state: LiveData<ShowDetailsUi> = _state

    fun init(
        showId: String,
        showsRepository: ShowsRepository,
        userRepository: UserRepository
    ) {
        this.userRepository = userRepository
        this.showsRepository = showsRepository
        viewModelScope.launch {
            val show = showsRepository.getShow(showId)
            _state.value = ShowDetailsUi(
                title = show.title,
                username = EmailParser.parseToUsername(userRepository.getUsername() ?: ""),
                ratingUi = RatingUi()
            )
        }
    }

    fun addReview(grade: Int, review: String) {
        _state.value = ShowDetailsUi(
            _state.value?.title,
            _state.value?.username,
            addReview(
                state.value?.ratingUi ?: RatingUi(),
                createReviewUi(grade, review)
            )
        )
    }

    private fun createReviewUi(grade: Int, review: String) = ReviewUi(
        userRepository.getUserAvatar(),
        _state.value?.username ?: "",
        grade,
        review,
    )

    private fun addReview(ratingUi: RatingUi, reviewUi: ReviewUi) = RatingUi(
        (ratingUi.numberOfReviews ?: 0) + 1,
        calculateNewAverageGrade(ratingUi, reviewUi),
        ratingUi.reviews.toMutableList().apply {
            add(0, reviewUi)
        },
    )

    private fun calculateNewAverageGrade(ratingUi: RatingUi, newReview: ReviewUi): Float {
        val sumOfGrades = ratingUi.reviews.sumOf { it.starGrade } + newReview.starGrade
        return sumOfGrades.toFloat() / (ratingUi.reviews.size + 1)
    }
}
