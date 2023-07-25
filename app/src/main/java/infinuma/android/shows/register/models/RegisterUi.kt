package infinuma.android.shows.register.models

data class RegisterUi(
    val email: String = "",
    val password: String = "",
    val repeatPassword: String = "",
    val registerButtonEnabled: Boolean = false,
)
