package infinuma.android.shows

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText
import java.util.regex.Pattern

class LoginActivity : Activity() {

    companion object {
        private const val EMAIL_REGEX_EXPRESSION = """[A-Za-z0-9.-]+@[A-Za-z-.]+\.[A-Za-z]+"""
        private const val PASSWORD_MIN_CHARACTERS = 6
    }

    private lateinit var emailInput: TextInputEditText
    private lateinit var passwordInput: TextInputEditText
    private lateinit var loginButton: Button

    private val emailPattern = Pattern.compile(EMAIL_REGEX_EXPRESSION)

    private val emailInputListener = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            loginButton.isEnabled = isInputValid(
                s.toString(),
                passwordInput.text.toString()
            )
            if (!isEmailValid(s.toString())) {
                emailInput.error = resources.getString(R.string.login_screen_email_error)
            }
        }
    }

    private val passwordInputListener = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(s: Editable?) {
            loginButton.isEnabled = isInputValid(
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

    private fun isInputValid(email: String, password: String) =
        isEmailValid(email) && password.isNotEmpty() && password.length >= PASSWORD_MIN_CHARACTERS

    private fun isEmailValid(email: String) =
        emailPattern.matcher(email).matches()

    private fun makeStatusBarTransparent() {
        window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        window?.statusBarColor = Color.TRANSPARENT
    }
}
