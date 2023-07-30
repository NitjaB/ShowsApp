package infinuma.android.shows.details.domain.mappers

import infinuma.android.shows.details.domain.models.Review
import infinuma.android.shows.network.models.ReviewResponse

class ReviewMapper {

    fun mapFromResponse(showId: String, response: ReviewResponse) =
        Review(
            id = response.id,
            avatarUrl = response.user.avatarUrl ?: "",
            review = response.comment,
            starGrade = response.rating,
            username = response.user.email,
            showId = showId
        )

    fun mapFromResponse(showId: String, response: List<ReviewResponse>) =
        response.map {
            mapFromResponse(showId, it)
        }
}