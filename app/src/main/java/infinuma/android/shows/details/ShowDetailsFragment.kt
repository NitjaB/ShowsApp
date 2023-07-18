package infinuma.android.shows.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import infinuma.android.shows.R
import infinuma.android.shows.databinding.ActivityShowDetailsBinding
import infinuma.android.shows.details.models.RatingUi
import infinuma.android.shows.details.models.ReviewUi
import infinuma.android.shows.shows.data.ShowsRepository

class ShowDetailsFragment : Fragment() {

    private lateinit var binding: ActivityShowDetailsBinding

    private val showsRepository = ShowsRepository()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = ActivityShowDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initScreen()
    }

    private fun initScreen() {
        // binding.titleTextView.text = showsRepository.getShow(getShowIdExtra(), baseContext)?.name
        binding.posterImageView.setBackgroundResource(R.drawable.ic_office_details)
        binding.descriptionTextView.text = resources.getString(R.string.show_details_screen_description)
        binding.ratingView.bind(createRatingUi())
        binding.addReviewButton.setOnClickListener {
            showAddReviewDialog()
        }
        binding.backButtonImageView.setOnClickListener {
            findNavController().popBackStack()
        }
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

    /*    private fun getShowIdExtra() =
            intent.getStringExtra(SHOW_ID) ?: SHOW_ID*/

    private fun showAddReviewDialog() {
        MaterialAlertDialogBuilder(requireContext(), R.style.AlertDialogTheme)
            .setTitle(R.string.show_details_screen_add_review_dialog_title)
            .setMessage(R.string.show_details_screen_add_review_dialog_message)
            .setPositiveButton(R.string.show_details_screen_add_review_dialog_confirmation_button, { _, _ -> addReview() })
            .setNegativeButton(R.string.show_details_screen_add_review_dialog_cancel_button, { _, _ -> })
            .setCancelable(false)
            .create()
            .show()
    }

    private fun addReview() {
        binding.ratingView.addReview(
            ReviewUi(
                avatar = R.drawable.ic_profile_picture,
                username = resources.getString(R.string.show_details_screen_reviews_username_3),
                starGrade = 3,
                review = resources.getString(R.string.show_details_screen_review_2)
            )
        )
    }
}
