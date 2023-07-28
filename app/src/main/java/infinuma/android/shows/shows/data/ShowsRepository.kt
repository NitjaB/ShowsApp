package infinuma.android.shows.shows.data

import infinuma.android.shows.details.domain.mappers.RatingMapper
import infinuma.android.shows.details.domain.mappers.ReviewMapper
import infinuma.android.shows.details.domain.models.Rating
import infinuma.android.shows.details.domain.models.Review
import infinuma.android.shows.network.ShowRemoteApi
import infinuma.android.shows.shows.domain.mappers.ShowInfoMapper

class ShowsRepository(
    private val showRemoteApi: ShowRemoteApi,
    private val showInfoMapper: ShowInfoMapper,
    private val ratingMapper: RatingMapper,
    private val reviewMapper: ReviewMapper,
) {

    suspend fun listShows(
    ) = showInfoMapper.fromResponse(
        showRemoteApi.listShows()
    )

    suspend fun getShow(showId: String) =
        showInfoMapper.fromResponse(showRemoteApi.getShow(showId))

    suspend fun getReviews(showId: String): Rating {
        val show = getShow(showId)
        val reviews = showRemoteApi.getReviews(showId)
        return ratingMapper.mapFromResource(
            numberOfReviews = show.numberOfReviews,
            averageGrade = show.averageRating ?: 0f,
            listReviewsResponse = reviews
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
