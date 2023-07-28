package infinuma.android.shows.details.mappers

import infinuma.android.shows.details.domain.models.Review
import infinuma.android.shows.details.models.ReviewUi

class ReviewUiMapper {

    fun fromDomain(review: Review) =
        ReviewUi(
            userAvatarUrl = review.avatarUrl,
            username = review.username,
            starGrade = review.starGrade,
            review = review.review,
        )
}