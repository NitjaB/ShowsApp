package infinuma.android.shows.shows.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import infinuma.android.shows.login.domain.UserRepository
import infinuma.android.shows.shows.data.ShowsRepository
import infinuma.android.shows.shows.mappers.ShowCardUiMapper
import infinuma.android.shows.shows.models.ShowCardUi
import infinuma.android.shows.shows.models.ShowUi
import infinuma.android.shows.utils.SingleLiveEvent
import java.lang.Exception
import kotlinx.coroutines.launch

class ShowViewModel : ViewModel() {

    private lateinit var userRepository: UserRepository
    private lateinit var showsRepository: ShowsRepository
    private lateinit var showCardUiMapper: ShowCardUiMapper

    private val _state = MutableLiveData(ShowUi())
    val state: LiveData<ShowUi> = _state

    private val _showImageUploadErrorDialog = SingleLiveEvent<Boolean>()
    val showImageUploadErrorDialog: LiveData<Boolean> = _showImageUploadErrorDialog

    fun init(
        showsRepository: ShowsRepository,
        userRepository: UserRepository,
        showCardUiMapper: ShowCardUiMapper,
    ) {
        this.userRepository = userRepository
        this.showsRepository = showsRepository
        viewModelScope.launch {
            val shows = mutableListOf<ShowCardUi>()
            try {
                val domainShows = showsRepository.listShows()
                shows.addAll(showCardUiMapper.mapFromDomain(domainShows))
            } catch (_: Exception) {
            }
            _state.value = ShowUi(
                this@ShowViewModel.userRepository.getSavedUser()?.avatarUrl ?: "",
                this@ShowViewModel.userRepository.getUsername(),
                shows = shows
            )
        }
    }

    fun hideShows() {
        _state.value = _state.value?.copy(
            shows = listOf()
        )
    }

    fun showShows() {
        _state.value = _state.value?.copy(
            shows = listOf()
        )
    }

    fun logout() {
        userRepository.setRememberedUser(false)
        userRepository.deleteUserAvatar()
    }

    fun changeProfilePicture(photo: Bitmap) {
        viewModelScope.launch {
            try {
                userRepository.setUserAvatar(photo)
            } catch (e: Exception) {
                _showImageUploadErrorDialog.value = true
            }
        }
        /*        _state.value = _state.value?.copy(
                    userAvatarUrl = photo
                )*/
    }
}
