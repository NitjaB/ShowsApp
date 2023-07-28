package infinuma.android.shows.details.mappers

import infinuma.android.shows.details.domain.models.Rating
import infinuma.android.shows.details.domain.models.Review
import infinuma.android.shows.details.models.RatingUi
import infinuma.android.shows.details.models.ReviewUi

class RatingUiMapper {

    fun fromDomain(rating: Rating) =
        RatingUi(
            numberOfReviews = rating.numberOfReviews,
            averageReviewGrade = rating.averageReviewGrade,
            reviews = mapReviews(rating.reviews)
        )

    private fun mapReviews(reviews: List<Review>) = reviews.map { review ->
        ReviewUi(
            review = review.review,
            userAvatarUrl = review.avatarUrl,
            starGrade = review.starGrade,
            username = review.username
        )
    }
}
