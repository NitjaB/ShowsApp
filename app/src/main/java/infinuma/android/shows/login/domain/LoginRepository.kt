package infinuma.android.shows.login.domain

import android.content.SharedPreferences

class LoginRepository(private val sharedPreferences: SharedPreferences) {
    companion object {
        private const val SHARED_PREFS_REMEMBER_ME = "sharedPrefsRememberMe"
    }
    fun isUserRemembered() = sharedPreferences.getBoolean(SHARED_PREFS_REMEMBER_ME, false)
    fun setRememberedUser(remember : Boolean) {
        with(sharedPreferences.edit()) {
            putBoolean(SHARED_PREFS_REMEMBER_ME, remember)
            apply()
        }
    }
}