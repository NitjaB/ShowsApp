package infinuma.android.shows.utils

import android.content.SharedPreferences
import infinuma.android.shows.login.domain.TokenRepository

object TokenRepositoryInstance {

    private lateinit var repository: TokenRepository

    fun init(sharedPreferences: SharedPreferences) {
        repository = TokenRepository(sharedPreferences)
    }

    fun get() = repository
}
