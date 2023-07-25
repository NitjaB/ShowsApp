package infinuma.android.shows.register.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import infinuma.android.shows.register.domain.RegisterInputError
import infinuma.android.shows.register.domain.RegisterInputValidator
import infinuma.android.shows.register.domain.RegistrationRepository
import infinuma.android.shows.register.models.RegisterUi
import infinuma.android.shows.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {
    private val _state = MutableLiveData(RegisterUi())
    val state: LiveData<RegisterUi> = _state
    private val _navigateToLogin = SingleLiveEvent<Boolean>()
    val navigateToLogin: LiveData<Boolean> = _navigateToLogin
    private val _showRegistrationErrorDialog = SingleLiveEvent<Boolean>()
    val showRegistrationErrorDialog: LiveData<Boolean> = _showRegistrationErrorDialog

    private lateinit var inputValidator: RegisterInputValidator
    private lateinit var registrationRepository: RegistrationRepository

    fun init(
        registerInputValidator: RegisterInputValidator,
        registrationRepository: RegistrationRepository,
    ) {
        this.inputValidator = registerInputValidator
        this.registrationRepository = registrationRepository
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

    fun onRegisterButtonClick() {
        viewModelScope.launch {
            try {
                registrationRepository.registerUser(
                    _state.value?.email ?: "",
                    _state.value?.password ?: "",
                    _state.value?.repeatPassword ?: "",
                )
                _navigateToLogin.value = true
            } catch (e: Exception) {
                _showRegistrationErrorDialog.value = true
            }
        }
    }

    private fun validate(
        email: String = _state.value?.email ?: "",
        password: String = _state.value?.password ?: "",
        repeatPassword: String = _state.value?.repeatPassword ?: ""
    ) = inputValidator.validate(email, password, repeatPassword)
}
