package infinuma.android.shows.register.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import infinuma.android.shows.register.domain.RegisterInputError
import infinuma.android.shows.register.domain.RegisterInputValidator
import infinuma.android.shows.register.models.RegisterUi

class RegisterViewModel : ViewModel() {
    private val _state = MutableLiveData(RegisterUi())
    val state: LiveData<RegisterUi> = _state

    private lateinit var inputValidator: RegisterInputValidator

    fun init(
        registerInputValidator: RegisterInputValidator,
    ) {
        this.inputValidator = registerInputValidator
    }

    fun onEmailInputChanged(input: String) {
        if (input != state.value?.email) {
            val inputErrors = validate(email = input)
            _state.value = state.value?.copy(
                email = input,
                isEmailError = inputErrors.contains(RegisterInputError.EMAIL_ERROR),
                registerButtonEnabled = inputErrors.isEmpty()
            )
        }
    }

    fun onPasswordInputChanged(input: String) {
        if (input != state.value?.password) {
            val inputErrors = validate(password = input)
            _state.value = state.value?.copy(
                password = input,
                registerButtonEnabled = inputErrors.isEmpty()
            )
        }
    }

    fun onRepeatPasswordInputChanged(input: String) {
        if (input != state.value?.repeatPassword) {
            val inputErrors = validate(repeatPassword = input)
            _state.value = state.value?.copy(
                repeatPassword = input,
                registerButtonEnabled = inputErrors.isEmpty()
            )
        }
    }

    private fun validate(
        email: String = _state.value?.email ?: "",
        password: String = _state.value?.password ?: "",
        repeatPassword: String = _state.value?.repeatPassword ?: ""
    ) = inputValidator.validate(email, password, repeatPassword)
}
