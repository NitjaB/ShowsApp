package infinuma.android.shows.register.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import infinuma.android.shows.register.models.RegisterUi

class RegisterViewModel : ViewModel() {
    private val _state = MutableLiveData(RegisterUi())
    val state: LiveData<RegisterUi> = _state

    fun onEmailInputChanged(input: String) {
        if (input != state.value?.email) {
            _state.value = state.value?.copy(
                email = input
            )
        }
    }

    fun onPasswordInputChanged(input: String) {
        if (input != state.value?.password) {
            _state.value = state.value?.copy(
                password = input
            )
        }
    }

    fun onRepeatPasswordInputChanged(input: String) {
        if (input != state.value?.repeatPassword) {
            _state.value = state.value?.copy(
                repeatPassword = input
            )
        }
    }
}
