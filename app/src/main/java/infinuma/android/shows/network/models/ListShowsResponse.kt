package infinuma.android.shows.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListShowsResponse(
    @SerialName("shows")
    val shows: List<ShowResponse>
)

@Serializable
data class ShowResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("average_rating")
    val average_rating: Float?,
    @SerialName("description")
    val description: String,
    @SerialName("image_url")
    val imageUrl: String,
    @SerialName("no_of_reviews")
    val numberOfReviews: Int,
    @SerialName("title")
    val title: String
)