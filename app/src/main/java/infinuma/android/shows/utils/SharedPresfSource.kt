package infinuma.android.shows.utils

import android.content.Context
import android.content.SharedPreferences

object SharedPrefsSource {
    private const val SHARED_PREFERENCES_NAME = "sharedPrefs"
    private lateinit var sharedPref: SharedPreferences

    fun init(appContext: Context) {
        sharedPref = appContext.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    fun getSharedPrefs() = sharedPref
}