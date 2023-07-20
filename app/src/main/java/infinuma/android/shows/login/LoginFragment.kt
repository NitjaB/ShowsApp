package infinuma.android.shows.login

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import infinuma.android.shows.R
import infinuma.android.shows.databinding.LoginActivityLayoutBinding
import infinuma.android.shows.login.domain.LoginInputValidator

class LoginFragment : Fragment() {

    companion object {
        private const val SHARED_PREFS_REMEMBER_ME = "sharedPrefsRememberMe"
    }

    private lateinit var binding: LoginActivityLayoutBinding

    private val inputValidator = LoginInputValidator()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = LoginActivityLayoutBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (getSharedPrefsRememberMe()) {
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
        val rememberMe = binding.saveLoginCheckBox.isChecked
        val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putBoolean(SHARED_PREFS_REMEMBER_ME, rememberMe)
            apply()
        }
        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToShowsFragment())
    }

    private fun getSharedPrefsRememberMe(): Boolean {
        val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)
        return sharedPref.getBoolean(SHARED_PREFS_REMEMBER_ME, false)
    }
}
