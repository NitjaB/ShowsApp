package infinuma.android.shows.main

import android.app.Activity
import android.os.Bundle
import infinuma.android.shows.utils.makeStatusBarTransparent

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        makeStatusBarTransparent()
    }
}