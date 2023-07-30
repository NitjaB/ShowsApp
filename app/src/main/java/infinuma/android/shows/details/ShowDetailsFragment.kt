package infinuma.android.shows.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import infinuma.android.shows.R
import infinuma.android.shows.database.RoomInstance
import infinuma.android.shows.databinding.ActivityShowDetailsBinding
import infinuma.android.shows.details.components.ratingView.RatingBottomSheetDialog
import infinuma.android.shows.details.domain.mappers.RatingMapper
import infinuma.android.shows.details.domain.mappers.ReviewMapper
import infinuma.android.shows.details.mappers.RatingUiMapper
import infinuma.android.shows.details.mappers.ReviewUiMapper
import infinuma.android.shows.details.viewModel.ShowDetailsViewModel
import infinuma.android.shows.login.domain.UserRepository
import infinuma.android.shows.login.domain.mappers.UserMapper
import infinuma.android.shows.network.RemoteApiSingleton
import infinuma.android.shows.shows.data.ShowsRepository
import infinuma.android.shows.shows.domain.mappers.ShowInfoMapper
import infinuma.android.shows.utils.NetworkConnection
import infinuma.android.shows.utils.SharedPrefsSource
import infinuma.android.shows.utils.loadWithGlide

class ShowDetailsFragment : Fragment() {

    private val viewModel by lazy { ViewModelProvider(this)[ShowDetailsViewModel::class.java] }

    private lateinit var binding: ActivityShowDetailsBinding

    private val args: ShowDetailsFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel.init(
            args.id,
            ShowsRepository(
                RemoteApiSingleton.getRemoteApi(),
                ShowInfoMapper(),
                RatingMapper(),
                ReviewMapper(),
                NetworkConnection(requireActivity().applicationContext),
                RoomInstance.get().reviewDao()
            ),
            UserRepository(
                sharedPreferences = SharedPrefsSource.getSharedPrefs(),
                context = requireContext(),
                showRemoteApi = RemoteApiSingleton.getRemoteApi(),
                userMapper = UserMapper()
            ),
            RatingUiMapper(),
            ReviewUiMapper()
        )
        binding = ActivityShowDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initScreen()
        viewModel.showDetailsInitErrorDialog.observe(viewLifecycleOwner) {
            showDetailsInitErrorDialog()
        }
        viewModel.showDetailsAddReviewErrorDialog.observe(viewLifecycleOwner) {
            showAddReviewErrorDialog()
        }
    }

    private fun initScreen() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            binding.titleTextView.text = state.title
            binding.ratingView.bind(state.ratingUi)
            binding.posterImageView.loadWithGlide(state.showImageUrl)
            binding.descriptionTextView.text = state.description
        }
        binding.descriptionTextView.text = resources.getString(R.string.show_details_screen_description)
        binding.addReviewButton.setOnClickListener {
            showAddReviewDialog()
        }
        binding.backButtonImageView.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun showAddReviewDialog() {
        RatingBottomSheetDialog(
            { grade, review -> viewModel.addReview(grade, review) },
            requireContext()
        ).show()
    }

    private fun showDetailsInitErrorDialog() = MaterialAlertDialogBuilder(requireContext(), R.style.AlertDialogTheme)
        .setTitle(R.string.show_deatils_screen_init_error_dialog_title)
        .setPositiveButton(R.string.OK) { _, _ -> }
        .setCancelable(false)
        .create()
        .show()

    private fun showAddReviewErrorDialog() = MaterialAlertDialogBuilder(requireContext(), R.style.AlertDialogTheme)
        .setTitle(R.string.show_details_screen_add_review_error_dialog_title)
        .setPositiveButton(R.string.OK) { _, _ -> }
        .setCancelable(false)
        .create()
        .show()
}
