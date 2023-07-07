package infinuma.android.shows

import android.app.Activity
import android.os.Bundle
import android.util.Log

class LoginActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("Login activity", "onCreate")
        setContentView(R.layout.login_activity_layout)
    }

    override fun onStart() {
        super.onStart()
        Log.i("Login activity", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("Login activity", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("Login activity", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("Login activity", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Login activity", "onDestroy")
    }

    /*
      Put the app in background and move it back to foreground
      onCreate()
      onStart()
      onResume() - then move to background
      onPause()
      onStop() - then move to foreground
      onStart()
      onResume()
    */

    /*
      Kill the app when it's in background
      onCreate()
      onStart()
      onResume() - then move to background
      onPause()
      onStop() - then kill app
      onDestroy()


      Kill the app when it's in foreground
      onCreate()
      onStart()
      onResume() - then kill app with call finish()
      onPause()
      onStop()
      onDestroy()
    */

    /*
      Lock the phones screen and unlock it
      onCreate()
      onStart()
      onResume() - then lock the phones screen
      onPause()
      onStop() - then unlock phones screen
      onStart()
      onResume()
    */
}
