package infinuma.android.shows

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.TextView

class WelcomeActivity : Activity() {

    companion object {

        private const val MAIL_TO_SCHEME = "mailto:"

        fun startActivityExplicitly(context: Context, email: String) {
            context.startActivity(Intent(context, WelcomeActivity::class.java).apply {
                putExtra(Intent.EXTRA_EMAIL, email)
            })
        }

        fun startActivityImplicitly(context: Context, email: String) {
            context.startActivity(Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse(MAIL_TO_SCHEME)
                putExtra(Intent.EXTRA_EMAIL, email)
            })
        }
    }

    private lateinit var helloTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome_activity_layout)
        helloTextView = findViewById(R.id.helloTextView)
        helloTextView.text = getString(R.string.welcome_screen_welcome_text, parseEmailToUsername(getEmailExtra()))
    }

    private fun parseEmailToUsername(email: String) = email.substringBefore("@")

    private fun getEmailExtra() = intent.getStringExtra(Intent.EXTRA_EMAIL) ?: Intent.EXTRA_EMAIL
}
