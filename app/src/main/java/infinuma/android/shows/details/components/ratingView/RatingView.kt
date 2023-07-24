package infinuma.android.shows.details.components.ratingView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import infinuma.android.shows.R
import infinuma.android.shows.databinding.ReviewLayoutBinding
import infinuma.android.shows.details.models.RatingUi

class RatingView(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {

    private val binding = ReviewLayoutBinding.inflate(LayoutInflater.from(context), this, true)

    private var ratingUi: RatingUi? = null

    private val adapter = ReviewAdapter(arrayListOf())

    init {
        binding.reviewsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@RatingView.adapter
        }
    }

    fun bind(ratingUi: RatingUi) {
        this.ratingUi = ratingUi
        if (ratingUi.numberOfReviews != null && ratingUi.averageReviewGrade != null) {
            binding.info.text =
                context.getString(R.string.show_details_screen_reviews_info, ratingUi.numberOfReviews, ratingUi.averageReviewGrade)
            ratingUi.averageReviewGrade?.let { grade ->
                binding.ratingBar.rating = grade
            }
            binding.reviewsRecyclerView.visibility = VISIBLE
            adapter.resetReviews(ratingUi.reviews)
        } else {
            binding.info.visibility = GONE
            binding.ratingBar.visibility = GONE
            binding.reviewsRecyclerView.visibility = GONE
            binding.noReviewsTextView.visibility = VISIBLE
        }
    }
}
