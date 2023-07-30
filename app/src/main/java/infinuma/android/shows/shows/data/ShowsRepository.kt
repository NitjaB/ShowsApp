package infinuma.android.shows.shows.data

import infinuma.android.shows.database.dao.ReviewDao
import infinuma.android.shows.details.domain.mappers.RatingMapper
import infinuma.android.shows.details.domain.mappers.ReviewMapper
import infinuma.android.shows.details.domain.models.Rating
import infinuma.android.shows.details.domain.models.Review
import infinuma.android.shows.network.ShowRemoteApi
import infinuma.android.shows.shows.domain.mappers.ShowInfoMapper
import infinuma.android.shows.utils.NetworkConnection

class ShowsRepository(
    private val showRemoteApi: ShowRemoteApi,
    private val showInfoMapper: ShowInfoMapper,
    private val ratingMapper: RatingMapper,
    private val reviewMapper: ReviewMapper,
    private val networkConnection: NetworkConnection,
    private val reviewDao: ReviewDao,
) {

    suspend fun listShows(
    ) = showInfoMapper.fromResponse(
        showRemoteApi.listShows()
    )

    suspend fun getShow(showId: String) =
        showInfoMapper.fromResponse(showRemoteApi.getShow(showId))

    suspend fun getReviews(showId: String): Rating {
        val isConnectedToInternet = networkConnection.isNetworkConnected()
        val reviews = mutableListOf<Review>()
        if (isConnectedToInternet) {
            reviews.addAll(reviewMapper.mapFromResponse(showRemoteApi.getReviews(showId).reviewsResponse))
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
        return reviewMapper.mapFromResponse(response.review)
    }
}
