package infinuma.android.shows.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListReviewsResponse(
    @SerialName("reviews")
    val reviewsResponse: List<ReviewResponse>
)
