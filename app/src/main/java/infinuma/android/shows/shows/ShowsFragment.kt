package infinuma.android.shows.shows

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
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
import infinuma.android.shows.login.LoginFragmentDirections
import infinuma.android.shows.login.domain.UserRepository
import infinuma.android.shows.logout.LogoutBottomSheetDialog
import infinuma.android.shows.logout.model.LogoutBottomSheetDialogUi
import infinuma.android.shows.shows.adapter.ShowsAdapter
import infinuma.android.shows.shows.data.ShowsRepository
import infinuma.android.shows.shows.viewmodel.ShowViewModel
import infinuma.android.shows.utils.FileUtil
import infinuma.android.shows.utils.SharedPrefsSource

class ShowsFragment : Fragment() {


    private val viewModel by lazy { ViewModelProvider(this)[ShowViewModel::class.java] }

    companion object {
        private const val CAMERA_REQUEST_CODE = 1175
    }

    private lateinit var binding: ActivityShowsBinding

    private val repository by lazy { ShowsRepository(requireContext()) }

    private lateinit var userRepository: UserRepository

    private val adapter = ShowsAdapter(arrayListOf()) {
        val action = ShowsFragmentDirections.actionShowsFragmentToShowDetailsFragment(it.id)
        findNavController().navigate(action)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        userRepository = UserRepository(SharedPrefsSource.getSharedPrefs(), requireContext())
        binding = ActivityShowsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.profilePictureImageView.setOnClickListener {
            LogoutBottomSheetDialog(
                LogoutBottomSheetDialogUi(
                    userRepository.getUsername() ?: "",
                    userRepository.getUserAvatar()

                ),
                {
                    showLogoutDialog()
                },
                {
                    handleChangeProfilePictureClick()
                },
                requireContext()
            ).show()
        }
        binding.profilePictureImageView.setImageBitmap(userRepository.getUserAvatar())
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
        adapter.addShows(repository.getShows())
    }

    private fun setUpToggleButton() {
        binding.toggleShowList.setOnClickListener {
            if (adapter.getShows().isEmpty()) {
                adapter.addShows(repository.getShows())
                binding.toggleShowList.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_trash_can)
                hideNoShowViews()
            } else {
                adapter.deleteShows()
                binding.toggleShowList.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_add)
                showNoShowsViews()
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
        userRepository.setRememberedUser(false)
        userRepository.deleteUserAvatar()
        findNavController().navigate(LoginFragmentDirections.actionGlobalLoginFragment())
    }

    private fun handleChangeProfilePictureClick() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAMERA_REQUEST_CODE) {
            val photo = data?.extras?.get("data") as Bitmap
            userRepository.setUserAvatar(photo)
            binding.profilePictureImageView.setImageDrawable(
                Drawable.createFromPath(FileUtil.getImageFile(requireContext())?.path)
            )
        }
    }
}
