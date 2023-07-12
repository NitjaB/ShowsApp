package infinuma.android.shows.shows

import android.app.Activity
import android.os.Bundle
import infinuma.android.shows.R
import infinuma.android.shows.utils.makeStatusBarTransparent

class ShowsActivity: Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        makeStatusBarTransparent()
        setContentView(R.layout.activity_shows)
    }
}