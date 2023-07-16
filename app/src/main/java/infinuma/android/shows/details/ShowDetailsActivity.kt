package infinuma.android.shows.details

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import infinuma.android.shows.R
import infinuma.android.shows.databinding.ActivityShowDetailsBinding
import infinuma.android.shows.details.models.RatingUi
import infinuma.android.shows.details.models.ReviewUi

class ShowDetailsActivity : Activity() {

    companion object {

        private const val SHOW_ID = "SHOW_ID"

        fun startActivity(context: Context, showId: String) {
            context.startActivity(Intent(context, ShowDetailsActivity::class.java).apply {
                putExtra(SHOW_ID, showId)
            })
        }
    }

    private lateinit var binding: ActivityShowDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initScreen()
    }

    private fun initScreen() {
        binding.posterImageView.setBackgroundResource(R.drawable.ic_office_details)
        binding.descriptionTextView.text = resources.getString(R.string.show_details_screen_description)
        binding.ratingView.bind(createRatingUi())
    }

    private fun createRatingUi() = RatingUi(
        3,
        3.67f,
        listOf(
            ReviewUi(
                avatar = R.drawable.ic_profile_picture,
                username = resources.getString(R.string.show_details_screen_reviews_username_1),
                starGrade = 3,
                review = resources.getString(R.string.show_details_screen_review_1)
            ),
            ReviewUi(
                avatar = R.drawable.ic_profile_picture,
                username = resources.getString(R.string.show_details_screen_reviews_username_2),
                starGrade = 3,
                review = null
            ),
            ReviewUi(
                avatar = R.drawable.ic_profile_picture,
                username = resources.getString(R.string.show_details_screen_reviews_username_3),
                starGrade = 3,
                review = resources.getString(R.string.show_details_screen_review_2)
            )
        )
    )

    private fun getShowIdExtra() =
        intent.getStringExtra(SHOW_ID)
}
