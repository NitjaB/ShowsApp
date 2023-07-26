package infinuma.android.shows.logout

import android.content.Context
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialog
import infinuma.android.shows.databinding.LogoutDialogBinding
import infinuma.android.shows.logout.model.LogoutBottomSheetDialogUi
import infinuma.android.shows.utils.loadWithGlide

class LogoutBottomSheetDialog(
    private val state: LogoutBottomSheetDialogUi,
    private val onLogoutClick: () -> Unit,
    private val onChangeProfilePictureClick: () -> Unit,
    context: Context
) : BottomSheetDialog(context) {

    private lateinit var binding: LogoutDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LogoutDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.usernameTextView.text = state.email
        binding.profilePictureImageView.loadWithGlide(state.avatar)
        binding.logoutButton.setOnClickListener {
            dismiss()
            onLogoutClick()
        }
        binding.changeProfilePictureButton.setOnClickListener {
            dismiss()
            onChangeProfilePictureClick()
        }
    }
}
