package infinuma.android.shows.shows

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import infinuma.android.shows.R
import infinuma.android.shows.databinding.ActivityShowsBinding
import infinuma.android.shows.details.domain.mappers.RatingMapper
import infinuma.android.shows.details.domain.mappers.ReviewMapper
import infinuma.android.shows.login.LoginFragmentDirections
import infinuma.android.shows.login.domain.UserRepository
import infinuma.android.shows.login.domain.mappers.UserMapper
import infinuma.android.shows.logout.LogoutBottomSheetDialog
import infinuma.android.shows.logout.model.LogoutBottomSheetDialogUi
import infinuma.android.shows.network.RemoteApiSingleton
import infinuma.android.shows.shows.adapter.ShowsAdapter
import infinuma.android.shows.shows.data.ShowsRepository
import infinuma.android.shows.shows.domain.mappers.ShowInfoMapper
import infinuma.android.shows.shows.mappers.ShowCardUiMapper
import infinuma.android.shows.shows.viewmodel.ShowViewModel
import infinuma.android.shows.utils.FileUtil
import infinuma.android.shows.utils.SharedPrefsSource
import infinuma.android.shows.utils.loadWithGlide

class ShowsFragment : Fragment() {

    private val viewModel by lazy { ViewModelProvider(this)[ShowViewModel::class.java] }

    companion object {
        private const val CAMERA_REQUEST_CODE = 1175
    }

    private lateinit var binding: ActivityShowsBinding

    private val adapter = ShowsAdapter(arrayListOf()) {
        val action = ShowsFragmentDirections.actionShowsFragmentToShowDetailsFragment(it.id)
        findNavController().navigate(action)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel.init(
            ShowsRepository(
                RemoteApiSingleton.getRemoteApi(),
                ShowInfoMapper(),
                RatingMapper(),
                ReviewMapper()
            ),
            UserRepository(
                sharedPreferences = SharedPrefsSource.getSharedPrefs(),
                context = requireContext(),
                showRemoteApi = RemoteApiSingleton.getRemoteApi(),
                userMapper = UserMapper()
            ),
            ShowCardUiMapper()
        )
        binding = ActivityShowsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.profilePictureImageView.setOnClickListener {
            LogoutBottomSheetDialog(
                state = LogoutBottomSheetDialogUi(
                    email = viewModel.state.value?.userEmail ?: "",
                    avatarUrl = viewModel.state.value?.userAvatarUrl ?: "",
                ),
                onLogoutClick = { showLogoutDialog() },
                onChangeProfilePictureClick = { handleChangeProfilePictureClick() },
                context = requireContext()
            ).show()
        }
        viewModel.state.observe(viewLifecycleOwner) {
            binding.profilePictureImageView.loadWithGlide(it.userAvatarUrl ?: "")
            adapter.deleteShows()
            adapter.addShows(it.shows)
            if (it.shows.isEmpty()) {
                binding.toggleShowList.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_add)
                showNoShowsViews()
            } else {
                binding.toggleShowList.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_trash_can)
                hideNoShowViews()
            }
        }
        viewModel.showImageUploadErrorDialog.observe(viewLifecycleOwner) {
            showImageUploadErrorDialog()
        }
        viewModel.showScreenErrorDialogTitle.observe(viewLifecycleOwner) {
            showScreenErrorDialogTitle()
        }
        setUpRecyclerView()
        setUpToggleButton()
    }

    private fun setUpRecyclerView() {
        binding.showsRecyclerView.apply {
            val manager = LinearLayoutManager(requireContext())
            val decoration = DividerItemDecoration(this.context, manager.orientation).apply {
                val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.show_divider)
                if (drawable != null) {
                    setDrawable(drawable)
                }
            }
            this.layoutManager = manager
            this.adapter = this@ShowsFragment.adapter
            this.addItemDecoration(decoration)
        }
    }

    private fun setUpToggleButton() {
        binding.toggleShowList.setOnClickListener {
            if (viewModel.state.value?.shows?.isEmpty() == true) {
                viewModel.showShows()
            } else {
                viewModel.hideShows()
            }
        }
    }

    private fun showNoShowsViews() {
        binding.noShowsImageView.visibility = View.VISIBLE
        binding.noShowsTextView.visibility = View.VISIBLE
    }

    private fun hideNoShowViews() {
        binding.noShowsImageView.visibility = View.GONE
        binding.noShowsTextView.visibility = View.GONE
    }

    private fun showLogoutDialog() {
        MaterialAlertDialogBuilder(requireContext(), R.style.AlertDialogTheme)
            .setTitle(R.string.logout_dialog_title)
            .setMessage(R.string.logout_dialog_message)
            .setPositiveButton(R.string.logout_dialog_confirmation_button, { _, _ -> logout() })
            .setNegativeButton(R.string.logout_dialog_cancel_button, { _, _ -> })
            .setCancelable(false)
            .create()
            .show()
    }

    private fun logout() {
        viewModel.logout()
        findNavController().navigate(LoginFragmentDirections.actionGlobalLoginFragment())
    }

    private fun handleChangeProfilePictureClick() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == CAMERA_REQUEST_CODE) {
            val photo = data?.extras?.get("data") as Bitmap
            viewModel.changeProfilePicture(photo)
        }
    }

    private fun showImageUploadErrorDialog() = MaterialAlertDialogBuilder(requireContext(), R.style.AlertDialogTheme)
        .setTitle(R.string.shows_screen_upload_title_error_dialog)
        .setPositiveButton(R.string.OK) { _, _ -> }
        .setCancelable(false)
        .create()
        .show()

    private fun showScreenErrorDialogTitle() = MaterialAlertDialogBuilder(requireContext(), R.style.AlertDialogTheme)
        .setTitle(R.string.shows_screen_error_dialog_title)
        .setPositiveButton(R.string.OK) { _, _ -> }
        .setCancelable(false)
        .create()
        .show()
}
