package infinuma.android.shows.details.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import infinuma.android.shows.details.domain.EmailParser
import infinuma.android.shows.details.mappers.RatingUiMapper
import infinuma.android.shows.details.mappers.ReviewUiMapper
import infinuma.android.shows.details.models.RatingUi
import infinuma.android.shows.details.models.ReviewUi
import infinuma.android.shows.details.models.ShowDetailsUi
import infinuma.android.shows.login.domain.UserRepository
import infinuma.android.shows.shows.data.ShowsRepository
import kotlinx.coroutines.launch

class ShowDetailsViewModel : ViewModel() {

    private lateinit var showId: String
    private lateinit var userRepository: UserRepository
    private lateinit var showsRepository: ShowsRepository
    private lateinit var ratingUiMapper: RatingUiMapper
    private lateinit var reviewUiMapper: ReviewUiMapper

    private val _state = MutableLiveData(ShowDetailsUi())
    val state: LiveData<ShowDetailsUi> = _state

    fun init(
        showId: String,
        showsRepository: ShowsRepository,
        userRepository: UserRepository,
        ratingUiMapper: RatingUiMapper,
        reviewUiMapper: ReviewUiMapper,
    ) {
        this.showId = showId
        this.userRepository = userRepository
        this.showsRepository = showsRepository
        this.ratingUiMapper = ratingUiMapper
        this.reviewUiMapper = reviewUiMapper
        viewModelScope.launch {
            val show = showsRepository.getShow(showId)
            val reviews = showsRepository.getReviews(showId)
            _state.value = ShowDetailsUi(
                title = show.title,
                showImageUrl = show.imageUrl,
                description = show.description,
                username = EmailParser.parseToUsername(userRepository.getUsername() ?: ""),
                ratingUi = ratingUiMapper.fromDomain(reviews)
            )
        }
    }

    fun addReview(grade: Int, review: String) {
        viewModelScope.launch {
            val review = showsRepository.addReview(
                showId = showId,
                rating = grade,
                review = review
            )
            reviewUiMapper.fromDomain(review)
            _state.value = _state.value?.copy(
                ratingUi = addReview(
                    state.value?.ratingUi ?: RatingUi(),
                    reviewUiMapper.fromDomain(review)
                )
            )
        }
    }

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
