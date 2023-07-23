package infinuma.android.shows.details


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import infinuma.android.shows.R
import infinuma.android.shows.databinding.ActivityShowDetailsBinding
import infinuma.android.shows.details.components.ratingView.RatingBottomSheetDialog
import infinuma.android.shows.details.models.ReviewUi
import infinuma.android.shows.details.viewModel.ShowDetailsViewModel
import infinuma.android.shows.login.domain.UserRepository
import infinuma.android.shows.shows.data.ShowsRepository
import infinuma.android.shows.utils.SharedPrefsSource

class ShowDetailsFragment : Fragment() {

    private val viewModel by lazy { ViewModelProvider(this)[ShowDetailsViewModel::class.java] }

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
        binding.ratingView.bind(showsRepository.getReviews())
        binding.addReviewButton.setOnClickListener {
            showAddReviewDialog()
        }
        binding.backButtonImageView.setOnClickListener {
            findNavController().popBackStack()
        }
    }

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
