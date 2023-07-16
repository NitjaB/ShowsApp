package infinuma.android.shows.details.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import infinuma.android.shows.R
import infinuma.android.shows.databinding.ReviewItemBinding
import infinuma.android.shows.databinding.ReviewLayoutBinding
import infinuma.android.shows.details.models.RatingUi
import infinuma.android.shows.details.models.ReviewUi

class RatingView(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {

    private val binding = ReviewLayoutBinding.inflate(LayoutInflater.from(context), this, true)

    private var ratingUi: RatingUi? = null

    fun bind(ratingUi: RatingUi) {
        this.ratingUi = ratingUi
        if (ratingUi.numberOfReviews != null && ratingUi.averageReviewGrade != null) {
            binding.info.text =
                context.getString(R.string.show_details_screen_reviews_info, ratingUi.numberOfReviews, ratingUi.averageReviewGrade)
            ratingUi.averageReviewGrade?.let { grade ->
                binding.ratingBar.rating = grade
            }
            setUpReviews(ratingUi.reviews)
        } else {
            binding.info.visibility = GONE
            binding.ratingBar.visibility = GONE
            binding.reviewsLinearLayout.visibility = GONE
            binding.noReviewsTextView.visibility = VISIBLE
        }
    }

    private fun setUpReviews(ratings: List<ReviewUi>) {
        ratings.forEachIndexed { index, item ->
            val reviewBinding = ReviewItemBinding.inflate(LayoutInflater.from(context)).apply {
                avatarImageView.setImageResource(item.avatar)
                usernameTextView.text = item.username
                ratingTextView.text = item.starGrade.toString()
                if(item.review.isNullOrBlank()) {
                    descriptionTextView.visibility = GONE
                } else {
                    descriptionTextView.text = item.review
                }
                if (index + 1 < ratings.size) {
                    divider.visibility = VISIBLE
                }
            }
            binding.reviewsLinearLayout.addView(reviewBinding.root)
        }
    }

    public fun addReview(reviewUi: ReviewUi) {
        ratingUi?.let {
            binding.reviewsLinearLayout.removeAllViews()
            val newRatingUi = RatingUi(
                (it.numberOfReviews ?: 0) + 1,
                calculateNewAverageGrade(it, reviewUi),
                it.reviews.toMutableList().apply {
                    add(0,reviewUi)
                },
            )
            ratingUi = newRatingUi
            bind(newRatingUi)
        }
    }

    private fun calculateNewAverageGrade(oldRatingUi: RatingUi, newReview: ReviewUi): Float {
        val sumOfGrades = oldRatingUi.reviews.sumOf { it.starGrade } + newReview.starGrade
        return sumOfGrades.toFloat() / (oldRatingUi.reviews.size + 1)
    }
}
