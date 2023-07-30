package infinuma.android.shows.shows.domain.mappers

import infinuma.android.shows.network.models.DisplayShowResponse
import infinuma.android.shows.network.models.ListShowsResponse
import infinuma.android.shows.network.models.ShowResponse
import infinuma.android.shows.shows.domain.models.ShowInfo

class ShowInfoMapper {

    fun fromResponse(response: DisplayShowResponse) = mapResponse(response.show)

    fun fromResponse(response: ListShowsResponse): List<ShowInfo> {
        val showsInfo = mutableListOf<ShowInfo>()
        response.shows.forEach { showsInfo.add(mapResponse(it)) }
        return showsInfo
    }

    private fun mapResponse(response: ShowResponse) =
        ShowInfo(
            id = response.id,
            averageRating = response.average_rating ?: 0f,
            description = response.description,
            imageUrl = response.imageUrl,
            numberOfReviews = response.numberOfReviews,
            title = response.title
        )
}