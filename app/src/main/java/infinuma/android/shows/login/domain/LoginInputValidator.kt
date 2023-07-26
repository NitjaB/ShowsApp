package infinuma.android.shows.login.domain

import java.util.regex.Pattern

class LoginInputValidator {
    companion object {
        private const val EMAIL_REGEX_EXPRESSION = """[A-Za-z0-9.-]+@[A-Za-z-.]+\.[A-Za-z]+"""
        private const val PASSWORD_MIN_CHARACTERS = 6
    }

    private val emailPattern = Pattern.compile(EMAIL_REGEX_EXPRESSION)

    fun validate(email: String, password: String): List<LoginInputError> {
        val errors = mutableListOf<LoginInputError>()
        if (!isEmailValid(email)) {
            errors.add(LoginInputError.EMAIL_ERROR)
        }
        if (password.length < PASSWORD_MIN_CHARACTERS) {
            errors.add(LoginInputError.PASSWORD_TO_SMALL)
        }
        return errors
    }

    private fun isEmailValid(email: String) =
        emailPattern.matcher(email).matches()
}

enum class LoginInputError {
    EMAIL_ERROR,
    PASSWORD_TO_SMALL,
}