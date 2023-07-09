package infinuma.android.shows

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView

class WelcomeActivity : Activity() {

    companion object {

        private const val EMAIL_EXTRA_KEY = "EMAIL_EXTRA_KEY"

        fun startActivity(context: Context, email: String) {
            context.startActivity(
                Intent(context, WelcomeActivity::class.java).apply {
                    putExtra(EMAIL_EXTRA_KEY, email)
                }
            )
        }
    }

    private lateinit var helloTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome_activity_layout)
        helloTextView = findViewById(R.id.helloTextView)
        helloTextView.text = getString(R.string.welcome_screen_welcome_text, parseEmailToUsername(getEmailExtra()))
    }

    private fun parseEmailToUsername(email: String) =
        email.substringBefore("@")

    private fun getEmailExtra() =
        intent.getStringExtra(EMAIL_EXTRA_KEY) ?: EMAIL_EXTRA_KEY
}
