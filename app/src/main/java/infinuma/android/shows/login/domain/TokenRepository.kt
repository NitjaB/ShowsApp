package infinuma.android.shows.login.domain

import android.content.SharedPreferences

class TokenRepository(
    private val sharedPreferences: SharedPreferences,
) {

    companion object {
        private const val SHARED_PREFS_USER_TOKEN = "sharedPrefsUserToken"
    }

    private var token = sharedPreferences.getString(SHARED_PREFS_USER_TOKEN, "")

    fun setUserToken(token: String) {
        if (token != this.token) {
            with(sharedPreferences.edit()) {
                putString(SHARED_PREFS_USER_TOKEN, token)
                apply()
            }
            this.token = token
        }
    }

    fun getToken() = token

    fun deleteToken() {
        token = ""
        with(sharedPreferences.edit()) {
            putString(SHARED_PREFS_USER_TOKEN, "")
            apply()
        }
    }
}
