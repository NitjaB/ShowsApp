package infinuma.android.shows.shows.data

import infinuma.android.shows.network.ShowRemoteApi
import infinuma.android.shows.shows.domain.mappers.ShowInfoMapper

class ShowsRepository(
    private val showRemoteApi: ShowRemoteApi,
    private val showInfoMapper: ShowInfoMapper,
) {

    suspend fun listShows(
    ) = showInfoMapper.fromResponse(
        showRemoteApi.listShows()
    )
}
