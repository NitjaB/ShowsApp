package infinuma.android.shows.details.domain.mappers

import infinuma.android.shows.details.domain.models.Review
import infinuma.android.shows.network.models.ReviewResponse

class ReviewMapper {

    fun mapFromResponse(response: ReviewResponse) =
        Review(
            id = response.id,
            avatarUrl = response.user.avatarUrl ?: "",
            review = response.comment,
            starGrade = response.rating,
            username = response.user.email
        )

    fun mapFromResponse(response: List<ReviewResponse>) =
        response.map {
            mapFromResponse(it)
        }
}