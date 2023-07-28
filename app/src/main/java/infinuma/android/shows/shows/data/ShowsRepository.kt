package infinuma.android.shows.shows.data

import infinuma.android.shows.details.domain.mappers.RatingMapper
import infinuma.android.shows.network.ShowRemoteApi
import infinuma.android.shows.shows.domain.mappers.ShowInfoMapper

class ShowsRepository(
    private val showRemoteApi: ShowRemoteApi,
    private val showInfoMapper: ShowInfoMapper,
    private val ratingMapper: RatingMapper,
) {

    suspend fun listShows(
    ) = showInfoMapper.fromResponse(
        showRemoteApi.listShows()
    )

    suspend fun getShow(showId: String) =
        showInfoMapper.fromResponse(showRemoteApi.getShow(showId))

    suspend fun getReviews(showId: String) {
        val show = getShow(showId)
        val reviews = showRemoteApi.getReviews(showId)
        ratingMapper.mapFromResource(
            numberOfReviews = show.numberOfReviews,
            averageGrade = show.averageRating ?: 0f,
            listReviewsResponse = reviews
        )
    }
}
