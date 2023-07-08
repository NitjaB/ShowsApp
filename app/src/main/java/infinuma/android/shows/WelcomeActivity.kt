package infinuma.android.shows

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle

class WelcomeActivity : Activity() {

    companion object {

        fun startActivity(context: Context) {
            context.startActivity(
                Intent(context, WelcomeActivity::class.java)
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome_activity_layout)
    }
}
