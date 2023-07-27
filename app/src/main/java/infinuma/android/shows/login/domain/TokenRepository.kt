package infinuma.android.shows.login.domain

import android.content.SharedPreferences

class TokenRepository(
    private val sharedPreferences: SharedPreferences,
) {

    companion object {
        private const val SHARED_PREFS_USER_TOKEN = "sharedPrefsUserToken"
        private const val CLIENT_TOKEN_SHARED_PREFS = "sharedPrefsClientToken"
        private const val UID_SHARED_PREFS = "uidSharedPrefs"
    }

    private var token = sharedPreferences.getString(SHARED_PREFS_USER_TOKEN, "")
    private var clientToken = sharedPreferences.getString(CLIENT_TOKEN_SHARED_PREFS, "")
    private var userId = sharedPreferences.getString(UID_SHARED_PREFS, "")

    fun setUserToken(token: String) {
        if (token != this.token) {
            with(sharedPreferences.edit()) {
                putString(SHARED_PREFS_USER_TOKEN, token)
                apply()
            }
            this.token = token
        }
    }

    fun setClientToken(token: String) {
        if (token != this.clientToken) {
            with(sharedPreferences.edit()) {
                putString(CLIENT_TOKEN_SHARED_PREFS, token)
                apply()
            }
            this.clientToken = token
        }
    }

    fun setUserId(userId: String) {
        if (userId != this.userId) {
            with(sharedPreferences.edit()) {
                putString(UID_SHARED_PREFS, userId)
                apply()
            }
            this.userId = userId
        }
    }

    fun getToken() = token

    fun getClientToken() = clientToken

    fun getUserId() = userId

    fun deleteToken() {
        token = ""
        clientToken = ""
        userId = ""
        with(sharedPreferences.edit()) {
            putString(SHARED_PREFS_USER_TOKEN, "")
            putString(CLIENT_TOKEN_SHARED_PREFS, "")
            putString(UID_SHARED_PREFS, "")
            apply()
        }
    }
}
