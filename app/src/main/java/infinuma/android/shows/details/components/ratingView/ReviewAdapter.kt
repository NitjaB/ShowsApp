package infinuma.android.shows.details.components.ratingView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import infinuma.android.shows.databinding.ReviewItemBinding
import infinuma.android.shows.details.models.ReviewUi

class ReviewAdapter(
    private var reviews: List<ReviewUi>,
) : RecyclerView.Adapter<ReviewViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {

        val binding = ReviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReviewViewHolder(binding)
    }

    override fun getItemCount() = reviews.size

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bind(reviews[position], position < itemCount - 1)
    }

    fun resetReviews(reviews: List<ReviewUi>) {
        this.reviews = reviews
        notifyDataSetChanged()
    }
}

class ReviewViewHolder(private val binding: ReviewItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        review: ReviewUi,
        isLast: Boolean,
    ) {
        binding.avatarImageView.setImageResource(review.avatar)
        binding.usernameTextView.text = review.username
        binding.descriptionTextView.text = review.review
        binding.ratingTextView.text = review.starGrade.toString()
        if (review.review.isNullOrBlank()) {
            binding.descriptionTextView.visibility = View.GONE
        } else {
            binding.descriptionTextView.visibility = View.VISIBLE
            binding.descriptionTextView.text = review.review
        }
        if (isLast) {
            binding.divider.visibility = FrameLayout.VISIBLE
        }
    }
}
