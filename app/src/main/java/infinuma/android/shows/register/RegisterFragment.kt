package infinuma.android.shows.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import infinuma.android.shows.R
import infinuma.android.shows.databinding.RegisterFragmentLayoutBinding
import infinuma.android.shows.login.LoginFragmentDirections
import infinuma.android.shows.network.RemoteApiSingleton
import infinuma.android.shows.register.domain.RegisterInputValidator
import infinuma.android.shows.register.domain.RegistrationRepository
import infinuma.android.shows.register.viewModel.RegisterViewModel

class RegisterFragment : Fragment() {

    private val viewModel by lazy { ViewModelProvider(this)[RegisterViewModel::class.java] }

    private lateinit var binding: RegisterFragmentLayoutBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = RegisterFragmentLayoutBinding.inflate(layoutInflater)
        viewModel.init(
            registerInputValidator = RegisterInputValidator(),
            registrationRepository = RegistrationRepository(RemoteApiSingleton.getRemoteApi())
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.state.observe(viewLifecycleOwner) {
            binding.usernameInputEditText.setText(it.email)
            binding.usernameInputEditText.placeCursorToEnd()
            if (it.isEmailError) {
                binding.usernameInputEditText.error = resources.getString(R.string.login_screen_email_error)
            }
            binding.passwordInputEditText.setText(it.password)
            binding.passwordInputEditText.placeCursorToEnd()
            binding.repeatPasswordInputEditText.setText(it.repeatPassword)
            binding.repeatPasswordInputEditText.placeCursorToEnd()
            binding.registerButton.isEnabled = it.registerButtonEnabled
        }
        viewModel.navigateToLogin.observe(viewLifecycleOwner) {
            findNavController().navigate(LoginFragmentDirections.actionGlobalLoginFragment())
        }
        viewModel.showRegistrationErrorDialog.observe(viewLifecycleOwner) {
            MaterialAlertDialogBuilder(requireContext(), R.style.AlertDialogTheme)
                .setTitle(R.string.register_screen_registration_failed_dialog_title)
                .setPositiveButton(R.string.OK) { _, _ -> }
                .setCancelable(false)
                .create()
                .show()
        }
        binding.usernameInputEditText.doOnTextChanged { text, _, _, _ ->
            viewModel.onEmailInputChanged(text.toString())
        }
        binding.passwordInputEditText.doOnTextChanged { text, _, _, _ ->
            viewModel.onPasswordInputChanged(text.toString())
        }
        binding.repeatPasswordInputEditText.doOnTextChanged { text, _, _, _ ->
            viewModel.onRepeatPasswordInputChanged(text.toString())
        }
        binding.registerButton.setOnClickListener {
            viewModel.onRegisterButtonClick()
        }
    }
}

fun EditText.placeCursorToEnd() {
    this.setSelection(this.text.length)
}
