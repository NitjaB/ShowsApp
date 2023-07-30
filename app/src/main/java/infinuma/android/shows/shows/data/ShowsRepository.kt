package infinuma.android.shows.shows.data

import infinuma.android.shows.database.dao.ReviewDao
import infinuma.android.shows.database.dao.ShowDao
import infinuma.android.shows.details.domain.mappers.RatingMapper
import infinuma.android.shows.details.domain.mappers.ReviewMapper
import infinuma.android.shows.details.domain.models.Rating
import infinuma.android.shows.details.domain.models.Review
import infinuma.android.shows.network.ShowRemoteApi
import infinuma.android.shows.shows.domain.mappers.ShowInfoMapper
import infinuma.android.shows.shows.domain.models.ShowInfo
import infinuma.android.shows.utils.NetworkConnection

class ShowsRepository(
    private val showRemoteApi: ShowRemoteApi,
    private val showInfoMapper: ShowInfoMapper,
    private val ratingMapper: RatingMapper,
    private val reviewMapper: ReviewMapper,
    private val networkConnection: NetworkConnection,
    private val reviewDao: ReviewDao,
    private val showDao: ShowDao
) {

    suspend fun listShows(): List<ShowInfo> {
        val isConnectedToInternet = networkConnection.isNetworkConnected()
        val shows = mutableListOf<ShowInfo>()
        if(isConnectedToInternet) {
            shows.addAll(showInfoMapper.fromResponse(showRemoteApi.listShows()))
            shows.forEach{showDao.insert(it)}
        } else{
            shows.addAll(showDao.getAllShows())
        }
        return shows
    }

    suspend fun getShow(showId: String) : ShowInfo {
        val show: ShowInfo
        val isConnectedToInternet = networkConnection.isNetworkConnected()
        if(isConnectedToInternet) {
            show = showInfoMapper.fromResponse(showRemoteApi.getShow(showId))
            showDao.insert(show)
        } else {
            show = showDao.getShowById(showId)
        }
        return show
    }

    suspend fun getReviews(showId: String): Rating {
        val isConnectedToInternet = networkConnection.isNetworkConnected()
        val reviews = mutableListOf<Review>()
        if (isConnectedToInternet) {
            reviews.addAll(reviewMapper.mapFromResponse(showId, showRemoteApi.getReviews(showId).reviewsResponse))
            reviews.forEach { reviewDao.insert(it) }
        } else {
            reviews.addAll(reviewDao.getAllReviews())
        }
        val show = getShow(showId)
        return ratingMapper.map(
            numberOfReviews = show.numberOfReviews,
            averageGrade = show.averageRating ?: 0f,
            reviews = reviews,
        )
    }

    suspend fun addReview(
        showId: String,
        rating: Int,
        review: String
    ): Review {
        val response = showRemoteApi.addReview(
            showId = showId,
            rating = rating,
            comment = review
        )
        return reviewMapper.mapFromResponse(showId, response.review)
    }
}
