package infinuma.android.shows.details.models

data class RatingUi(
    val numberOfReviews: Int? = null,
    val averageReviewGrade: Float? = null,
    val reviews: List<ReviewUi> = listOf()
) {

    fun addReview(reviewUi: ReviewUi) = RatingUi(
        (numberOfReviews ?: 0) + 1,
        calculateNewAverageGrade(reviewUi),
        reviews.toMutableList().apply {
            add(0, reviewUi)
        },
    )

    private fun calculateNewAverageGrade(newReview: ReviewUi): Float {
        val sumOfGrades = reviews.sumOf { it.starGrade } + newReview.starGrade
        return sumOfGrades.toFloat() / (reviews.size + 1)
    }
}

