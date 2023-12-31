package infinuma.android.shows.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListShowsResponse(
    @SerialName("shows")
    val shows: List<ShowResponse>
)
