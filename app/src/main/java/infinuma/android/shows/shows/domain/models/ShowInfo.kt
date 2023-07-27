package infinuma.android.shows.shows.domain.models

data class ShowInfo(
    val id: Int,
    val averageRating: Float?,
    val description: String,
    val imageUrl: String,
    val numberOfReviews: Int,
    val title: String
)
