package infinuma.android.shows.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RegisterUserResponse(
    @SerialName("user")
    val user: RegisterUserUserResponse
)

@Serializable
data class RegisterUserUserResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("email")
    val email: String,
)
