package infinuma.android.shows.login.models

data class LoginUi (
    val title: String = "",
    val email: String = "",
    val isEmailError: Boolean = false,
    val password: String = "",
    val loginButtonEnabled: Boolean = false,
    val registerButtonEnabled: Boolean = false
)