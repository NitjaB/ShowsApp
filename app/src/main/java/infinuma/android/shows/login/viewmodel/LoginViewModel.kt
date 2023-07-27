package infinuma.android.shows.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import infinuma.android.shows.login.domain.LoginInputError
import infinuma.android.shows.login.domain.LoginInputValidator
import infinuma.android.shows.login.domain.TokenRepository
import infinuma.android.shows.login.domain.UserRepository
import infinuma.android.shows.login.models.LoginUi
import infinuma.android.shows.utils.SingleLiveEvent
import java.lang.Exception
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _state = MutableLiveData(LoginUi())
    val state: LiveData<LoginUi> = _state

    private val _navigateToShowScreen = SingleLiveEvent<Boolean>()
    val navigateToShowScreen: LiveData<Boolean> = _navigateToShowScreen

    private val _showErrorWhileLoginDialog = SingleLiveEvent<Boolean>()
    val showErrorWhileLoginDialog: LiveData<Boolean> = _showErrorWhileLoginDialog

    private lateinit var inputValidator: LoginInputValidator
    private lateinit var userRepository: UserRepository

    fun init(
        loginInputValidator: LoginInputValidator,
        userRepository: UserRepository,
        tokenRepository: TokenRepository,
    ) {
        this.inputValidator = loginInputValidator
        this.userRepository = userRepository
        if (userRepository.isUserRemembered()) {
            _navigateToShowScreen.value = true
        } else {
            userRepository.deleteUserAvatar()
            tokenRepository.deleteToken()
        }
    }

    fun onEmailInputChanged(input: String) {
        if (input != state.value?.email) {
            val inputErrors = validate(email = input)
            _state.value = state.value?.copy(
                email = input,
                isEmailError = inputErrors.contains(LoginInputError.EMAIL_ERROR),
                loginButtonEnabled = inputErrors.isEmpty()
            )
        }
    }

    fun onPasswordInputChanged(input: String) {
        if (input != state.value?.password) {
            val inputErrors = validate(password = input)
            _state.value = state.value?.copy(
                password = input,
                loginButtonEnabled = inputErrors.isEmpty()
            )
        }
    }

    fun onLoginButtonClick() {
        _state.value = _state.value?.copy(
            isLoading = true
        )
        viewModelScope.launch {
            try {
                userRepository.login(
                    email = state.value?.email ?: "",
                    password = state.value?.password ?: "",
                )
                delay(10000)
                _state.value = _state.value?.copy(
                    isLoading = false
                )
                _navigateToShowScreen.value = true
            } catch (e: Exception) {
                _showErrorWhileLoginDialog.value = true
                _state.value = _state.value?.copy(
                    isLoading = false
                )
            }
        }
    }

    fun userRegistered(didRegister: Boolean) {
        _state.value = _state.value?.copy(
            didUserRegister = didRegister
        )
    }

    private fun validate(
        email: String = _state.value?.email ?: "",
        password: String = _state.value?.password ?: "",
    ) = inputValidator.validate(email, password)
}
