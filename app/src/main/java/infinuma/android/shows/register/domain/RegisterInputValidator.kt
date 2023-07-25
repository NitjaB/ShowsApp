package infinuma.android.shows.register.domain

import java.util.regex.Pattern

class RegisterInputValidator {

    companion object {
        private const val EMAIL_REGEX_EXPRESSION = """[A-Za-z0-9.-]+@[A-Za-z-.]+\.[A-Za-z]+"""
        private const val PASSWORD_MIN_CHARACTERS = 6
    }

    private val emailPattern = Pattern.compile(EMAIL_REGEX_EXPRESSION)

    fun validate(email: String, password: String, repeatPassword: String): List<RegisterInputError> {
        val errors = mutableListOf<RegisterInputError>()
        if (!isEmailValid(email)) {
            errors.add(RegisterInputError.EMAIL_ERROR)
        }
        if (password.length < PASSWORD_MIN_CHARACTERS) {
            errors.add(RegisterInputError.PASSWORD_TO_SMALL)
        }
        if (password != repeatPassword) {
            errors.add(RegisterInputError.PASSWORD_NOT_MATCHING_ERROR)
        }
        return errors
    }

    private fun isEmailValid(email: String) =
        emailPattern.matcher(email).matches()
}

enum class RegisterInputError {
    EMAIL_ERROR,
    PASSWORD_TO_SMALL,
    PASSWORD_NOT_MATCHING_ERROR,
}