package infinuma.android.shows.shows

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.View
import infinuma.android.shows.R

class ShowsActivity: Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        makeStatusBarTransparent()
        setContentView(R.layout.activity_shows)
    }

    private fun makeStatusBarTransparent() {
        window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        window?.statusBarColor = Color.TRANSPARENT
    }
}