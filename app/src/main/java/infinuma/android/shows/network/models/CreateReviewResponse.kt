package infinuma.android.shows.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateReviewResponse(
    @SerialName("review")
    val review: ReviewResponse,
)
