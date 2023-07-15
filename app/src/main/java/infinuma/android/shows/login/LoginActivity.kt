package infinuma.android.shows.login

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.View.OnClickListener
import infinuma.android.shows.R
import infinuma.android.shows.databinding.LoginActivityLayoutBinding
import infinuma.android.shows.login.domain.LoginInputValidator
import infinuma.android.shows.welcome.WelcomeActivity

class LoginActivity : Activity() {

    private lateinit var binding: LoginActivityLayoutBinding

    private val inputValidator = LoginInputValidator()

    private val emailInputListener = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            binding.loginButton.isEnabled = inputValidator.isInputValid(
                s.toString(),
                binding.passwordInputEditText.text.toString()
            )
            if (!inputValidator.isEmailValid(s.toString())) {
                binding.usernameInputEditText.error = resources.getString(R.string.login_screen_email_error)
            }
        }
    }

    private val passwordInputListener = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            binding.loginButton.isEnabled = inputValidator.isInputValid(
                binding.usernameInputEditText.text.toString(),
                s.toString()
            )
        }
    }

    private val buttonClickListener = OnClickListener {
        WelcomeActivity.startActivityImplicitly(this, binding.usernameInputEditText.text.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        makeStatusBarTransparent()
        binding = LoginActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.usernameInputEditText.addTextChangedListener(emailInputListener)
        binding.passwordInputEditText.addTextChangedListener(passwordInputListener)
        binding.loginButton.setOnClickListener(buttonClickListener)
    }

    private fun makeStatusBarTransparent() {
        window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        window?.statusBarColor = Color.TRANSPARENT
    }
}
