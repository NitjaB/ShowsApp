package infinuma.android.shows.details.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import infinuma.android.shows.details.domain.EmailParser
import infinuma.android.shows.details.models.RatingUi
import infinuma.android.shows.details.models.ReviewUi
import infinuma.android.shows.details.models.ShowDetailsUi
import infinuma.android.shows.login.domain.UserRepository
import infinuma.android.shows.shows.data.ShowsRepository

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
        _state.value = ShowDetailsUi(
            title = showsRepository.getShow(showId)?.name,
            username = EmailParser.parseToUsername(userRepository.getUsername() ?: ""),
            ratingUi = showsRepository.getReviews()
        )
    }

    fun addReview(grade: Int, review: String) {
        _state.value = ShowDetailsUi(
            _state.value?.title,
            _state.value?.username,
            state.value?.ratingUi?.addReview(createReviewUi(grade, review)) ?: RatingUi()
        )
    }

    private fun createReviewUi(grade: Int, review: String) = ReviewUi(
        userRepository.getUserAvatar(),
        _state.value?.username ?: "",
        grade,
        review,
    )
}
