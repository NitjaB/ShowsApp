package infinuma.android.shows.details.domain.mappers

import infinuma.android.shows.details.domain.models.Rating
import infinuma.android.shows.details.domain.models.Review
import infinuma.android.shows.network.models.ListReviewsResponse

class RatingMapper {

    fun mapFromResource(
        numberOfReviews: Int,
        averageGrade: Float,
        listReviewsResponse: ListReviewsResponse
    ) = Rating(
        numberOfReviews = numberOfReviews,
        averageReviewGrade = averageGrade,
        reviews = mapFromResource(listReviewsResponse)
    )

    private fun mapFromResource(listReviewsResponse: ListReviewsResponse) =
        listReviewsResponse.reviewsResponse.map { response ->
            Review(
                id = response.id,
                avatarUrl = response.user.avatarUrl ?: "",
                review = response.comment,
                starGrade = response.rating,
                username = response.user.email
            )
        }
}