package infinuma.android.shows.details


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import infinuma.android.shows.R
import infinuma.android.shows.databinding.ActivityShowDetailsBinding
import infinuma.android.shows.details.components.ratingView.RatingBottomSheetDialog
import infinuma.android.shows.details.models.RatingUi
import infinuma.android.shows.details.models.ReviewUi
import infinuma.android.shows.login.domain.UserRepository
import infinuma.android.shows.shows.data.ShowsRepository
import infinuma.android.shows.utils.SharedPrefsSource

class ShowDetailsFragment : Fragment() {

    private lateinit var binding: ActivityShowDetailsBinding

    private val args: ShowDetailsFragmentArgs by navArgs()

    private val showsRepository by lazy { ShowsRepository(requireContext()) }

    private lateinit var userRepository: UserRepository

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        userRepository = UserRepository(SharedPrefsSource.getSharedPrefs(), requireContext())
        binding = ActivityShowDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initScreen()
    }

    private fun initScreen() {
        binding.titleTextView.text = showsRepository.getShow(args.id)?.name
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
                avatar = BitmapFactory.decodeResource(resources, R.drawable.ic_profile_picture),
                username = resources.getString(R.string.show_details_screen_reviews_username_1),
                starGrade = 3,
                review = resources.getString(R.string.show_details_screen_review_1)
            ),
            ReviewUi(
                avatar = BitmapFactory.decodeResource(resources, R.drawable.ic_profile_picture),
                username = resources.getString(R.string.show_details_screen_reviews_username_2),
                starGrade = 3,
                review = null
            ),
            ReviewUi(
                avatar = BitmapFactory.decodeResource(resources, R.drawable.ic_profile_picture),
                username = resources.getString(R.string.show_details_screen_reviews_username_3),
                starGrade = 3,
                review = resources.getString(R.string.show_details_screen_review_2)
            )
        )
    )

    /*    private fun getShowIdExtra() =
            intent.getStringExtra(SHOW_ID) ?: SHOW_ID*/

    private fun showAddReviewDialog() {
        RatingBottomSheetDialog(
            { grade, review -> addReview(grade, review) },
            requireContext()
        ).show()
    }

    private fun parseEmailToUsername(email: String) =
        email.substringBefore("@")

    private fun addReview(
        grade: Int,
        review: String,
    ) {
        binding.ratingView.addReview(
            ReviewUi(
                avatar = userRepository.getUserAvatar(),
                username = parseEmailToUsername(userRepository.getUsername() ?: ""),
                starGrade = grade,
                review = review,
            )
        )
    }
}
