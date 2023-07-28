package infinuma.android.shows.details.models

data class ShowDetailsUi(
    val title: String? = null,
    val showImageUrl: String = "",
    val description: String = "",
    val username: String? = null,
    val ratingUi: RatingUi = RatingUi()
)
