package infinuma.android.shows.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReviewResponse(
    @SerialName("id")
    val id: String,
    @SerialName("comment")
    val comment: String,
    @SerialName("rating")
    val rating: Int,
    @SerialName("user")
    val user: RegisterUserUserResponse
)
