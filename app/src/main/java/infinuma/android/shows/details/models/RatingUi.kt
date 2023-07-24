package infinuma.android.shows.details.models

data class RatingUi(
    val numberOfReviews: Int? = null,
    val averageReviewGrade: Float? = null,
    val reviews: List<ReviewUi> = listOf()
)
