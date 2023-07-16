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

    fun bind(ratingUi: RatingUi) {
        if(ratingUi.numberOfReviews != null && ratingUi.averageReviewGrade != null) {
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
}
