package infinuma.android.shows.details.components.ratingView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import infinuma.android.shows.R
import infinuma.android.shows.databinding.ReviewLayoutBinding
import infinuma.android.shows.details.models.RatingUi
import infinuma.android.shows.details.models.ReviewUi

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

    public fun addReview(reviewUi: ReviewUi) {
        ratingUi?.let {
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
