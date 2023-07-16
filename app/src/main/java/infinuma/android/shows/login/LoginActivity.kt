package infinuma.android.shows.login

import android.app.Activity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import infinuma.android.shows.R
import infinuma.android.shows.databinding.LoginActivityLayoutBinding
import infinuma.android.shows.login.domain.LoginInputValidator
import infinuma.android.shows.shows.ShowsActivity
import infinuma.android.shows.utils.makeStatusBarTransparent

class LoginActivity : Activity() {

    private lateinit var binding: LoginActivityLayoutBinding

    private val inputValidator = LoginInputValidator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        makeStatusBarTransparent()
        binding = LoginActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.usernameInputEditText.addTextChangedListener(
            afterTextChanged = { email -> handleEmailInputChange(email.toString()) }
        )
        binding.passwordInputEditText.addTextChangedListener(
            afterTextChanged = { password -> handlePasswordInputChange(password.toString()) }
        )
        binding.loginButton.setOnClickListener {
            ShowsActivity.startActivity(this)
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
}
