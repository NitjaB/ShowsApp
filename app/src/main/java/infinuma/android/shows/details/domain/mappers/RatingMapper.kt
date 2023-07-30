package infinuma.android.shows.details.domain.mappers

import infinuma.android.shows.details.domain.models.Rating
import infinuma.android.shows.details.domain.models.Review
import infinuma.android.shows.network.models.ListReviewsResponse

class RatingMapper {

    fun map(
        numberOfReviews: Int,
        averageGrade: Float,
        reviews: List<Review>
    ) = Rating(
        numberOfReviews = numberOfReviews,
        averageReviewGrade = averageGrade,
        reviews = reviews
    )
}