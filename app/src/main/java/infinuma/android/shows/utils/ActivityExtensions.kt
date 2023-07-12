package infinuma.android.shows.utils

import android.app.Activity
import android.graphics.Color
import android.view.View

fun Activity.makeStatusBarTransparent() {
    window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    window?.statusBarColor = Color.TRANSPARENT
}
