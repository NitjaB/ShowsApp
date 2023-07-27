package infinuma.android.shows.shows.domain.mappers

import infinuma.android.shows.network.models.ListShowsResponse
import infinuma.android.shows.shows.domain.models.ShowInfo

class ShowInfoMapper {

    fun fromResponse(response: ListShowsResponse): List<ShowInfo> {
        val showsInfo = mutableListOf<ShowInfo>()
        response.shows.forEach {
            showsInfo.add(
                ShowInfo(
                    id = it.id,
                    averageRating = it.average_rating ?: 0f,
                    description = it.description ?: "",
                    imageUrl = it.imageUrl,
                    numberOfReviews = it.numberOfReviews,
                    title = it.title
                )
            )
        }
        return showsInfo
    }
}