package infinuma.android.shows.shows.domain.mappers

import infinuma.android.shows.network.models.DisplayShowResponse
import infinuma.android.shows.network.models.ListShowsResponse
import infinuma.android.shows.network.models.ShowResponse
import infinuma.android.shows.shows.domain.models.ShowInfo

class ShowInfoMapper {

    fun fromResponse(response: DisplayShowResponse) = mapShow(response.show)

    fun fromResponse(response: ListShowsResponse): List<ShowInfo> {
        val showsInfo = mutableListOf<ShowInfo>()
        response.shows.forEach { showsInfo.add(mapShow(it)) }
        return showsInfo
    }

    private fun mapShow(show: ShowResponse) =
        ShowInfo(
            id = show.id,
            averageRating = show.average_rating ?: 0f,
            description = show.description ?: "",
            imageUrl = show.imageUrl,
            numberOfReviews = show.numberOfReviews,
            title = show.title
        )
}