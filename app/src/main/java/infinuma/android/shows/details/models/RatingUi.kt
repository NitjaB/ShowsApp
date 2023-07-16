package infinuma.android.shows.details.models

data class RatingUi(
    val numberOfReviews: Int?,
    val averageReviewGrade: Float?,
    val reviews: List<ReviewUi>
)
