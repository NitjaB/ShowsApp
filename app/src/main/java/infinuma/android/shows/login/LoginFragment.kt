package infinuma.android.shows.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import infinuma.android.shows.R
import infinuma.android.shows.databinding.LoginActivityLayoutBinding
import infinuma.android.shows.login.domain.LoginInputValidator
import infinuma.android.shows.login.domain.UserRepository
import infinuma.android.shows.utils.SharedPrefsSource

class LoginFragment : Fragment() {

    private lateinit var binding: LoginActivityLayoutBinding

    private val inputValidator = LoginInputValidator()

    private lateinit var userRepository: UserRepository

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        userRepository = UserRepository(SharedPrefsSource.getSharedPrefs(), requireContext())
        binding = LoginActivityLayoutBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (userRepository.isUserRemembered()) {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToShowsFragment())
        } else {
            binding.usernameInputEditText.addTextChangedListener(
                afterTextChanged = { email -> handleEmailInputChange(email.toString()) }
            )
            binding.passwordInputEditText.addTextChangedListener(
                afterTextChanged = { password -> handlePasswordInputChange(password.toString()) }
            )
            binding.loginButton.setOnClickListener {
                handleLoginButtonClick()
            }
        }
    }

    private fun handleEmailInputChange(email: String) {
        binding.loginButton.isEnabled = inputValidator.isInputValid(
            email,
            binding.passwordInputEditText.text.toString()
        )
        if (!inputValidator.isEmailValid(email)) {
            binding.usernameInputEditText.error = resources.getString(R.string.login_screen_email_error)
        }
    }

    private fun handlePasswordInputChange(password: String) {
        binding.loginButton.isEnabled = inputValidator.isInputValid(
            binding.usernameInputEditText.text.toString(),
            password,
        )
    }

    private fun handleLoginButtonClick() {
        userRepository.setRememberedUser(binding.saveLoginCheckBox.isChecked)
        userRepository.saveUsername(binding.usernameInputEditText.text.toString())
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToShowsFragment())
    }
}
