package infinuma.android.shows.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DisplayShowResponse(
    @SerialName("show")
    val show: ShowResponse
)
