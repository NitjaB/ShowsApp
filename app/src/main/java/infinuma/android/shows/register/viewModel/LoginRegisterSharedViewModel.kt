package infinuma.android.shows.register.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginRegisterSharedViewModel : ViewModel() {

    private val _didUserRegister = MutableLiveData(false)
    val didUserRegister = _didUserRegister

    fun userRegistered() {
        _didUserRegister.value = true
    }
}
