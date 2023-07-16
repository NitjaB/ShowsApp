package infinuma.android.shows.details.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import infinuma.android.shows.R
import infinuma.android.shows.databinding.ReviewLayoutBinding
import infinuma.android.shows.details.models.RatingUi

class RatingView(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {

    private val binding = ReviewLayoutBinding.inflate(LayoutInflater.from(context), this, true)

    fun bind(ratingUi: RatingUi) {
        binding.info.text =
            context.getString(R.string.show_details_screen_reviews_info, ratingUi.numberOfReviews, ratingUi.averageReviewGrade)
        binding.ratingBar.rating = ratingUi.averageReviewGrade
    }
}
