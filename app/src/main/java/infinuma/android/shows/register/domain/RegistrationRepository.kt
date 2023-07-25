package infinuma.android.shows.register.domain

import infinuma.android.shows.network.ShowRemoteApi

class RegistrationRepository(
    private val api: ShowRemoteApi
) {

    suspend fun registerUser(
        email: String,
        password: String,
        repeatPassword: String,
    ) = api.registerUser(email, password, repeatPassword)
}
