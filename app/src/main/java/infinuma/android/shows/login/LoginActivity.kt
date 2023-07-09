package infinuma.android.shows.login

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import infinuma.android.shows.R
import infinuma.android.shows.login.domain.LoginInputValidator
import infinuma.android.shows.welcome.WelcomeActivity
import java.util.regex.Pattern

class LoginActivity : Activity() {

    private lateinit var emailInput: TextInputEditText
    private lateinit var passwordInput: TextInputEditText
    private lateinit var loginButton: Button

    private val inputValidator = LoginInputValidator()

    private val emailInputListener = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            loginButton.isEnabled = inputValidator.isInputValid(
                s.toString(),
                passwordInput.text.toString()
            )
            if (!inputValidator.isEmailValid(s.toString())) {
                emailInput.error = resources.getString(R.string.login_screen_email_error)
            }
        }
    }

    private val passwordInputListener = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            loginButton.isEnabled = inputValidator.isInputValid(
                emailInput.text.toString(),
                s.toString()
            )
        }
    }

    private val buttonClickListener = OnClickListener {
        WelcomeActivity.startActivityImplicitly(this, emailInput.text.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        makeStatusBarTransparent()
        setContentView(R.layout.login_activity_layout)
        emailInput = findViewById(R.id.usernameInputEditText)
        passwordInput = findViewById(R.id.passwordInputEditText)
        loginButton = findViewById(R.id.loginButton)
        emailInput.addTextChangedListener(emailInputListener)
        passwordInput.addTextChangedListener(passwordInputListener)
        loginButton.setOnClickListener(buttonClickListener)
    }

    private fun makeStatusBarTransparent() {
        window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        window?.statusBarColor = Color.TRANSPARENT
    }
}
