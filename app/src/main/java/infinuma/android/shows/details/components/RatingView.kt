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
        binding.info.text =
            context.getString(R.string.show_details_screen_reviews_info, ratingUi.numberOfReviews, ratingUi.averageReviewGrade)
        binding.ratingBar.rating = ratingUi.averageReviewGrade
        setUpReviews(ratingUi.reviews)
    }

    private fun setUpReviews(ratings: List<ReviewUi>) {
        ratings.forEach { item ->
            val reviewBinding = ReviewItemBinding.inflate(LayoutInflater.from(context)).apply {
                avatarImageView.setImageResource(item.avatar)
                usernameTextView.text = item.username
                ratingTextView.text = item.starGrade.toString()
                descriptionTextView.text = item.review
            }
            binding.reviewsLinearLayout.addView(reviewBinding.root)
        }
    }
}
