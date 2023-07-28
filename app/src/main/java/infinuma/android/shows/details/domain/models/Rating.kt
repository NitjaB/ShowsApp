package infinuma.android.shows.details.domain.models

data class Rating (
    val numberOfReviews: Int,
    val averageReviewGrade: Float,
    val reviews: List<Review>
)
