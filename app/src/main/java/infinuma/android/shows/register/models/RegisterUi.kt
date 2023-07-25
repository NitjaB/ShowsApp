package infinuma.android.shows.register.models

data class RegisterUi(
    val email: String = "",
    val isEmailError: Boolean = false,
    val password: String = "",
    val repeatPassword: String = "",
    val registerButtonEnabled: Boolean = false,
)
