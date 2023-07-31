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
import java.lang.NullPointerException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShowViewModel : ViewModel() {

    private lateinit var userRepository: UserRepository
    private lateinit var showsRepository: ShowsRepository
    private lateinit var showCardUiMapper: ShowCardUiMapper

    private val _state = MutableLiveData(ShowUi())
    val state: LiveData<ShowUi> = _state

    private val _showImageUploadErrorDialog = SingleLiveEvent<Boolean>()
    val showImageUploadErrorDialog: LiveData<Boolean> = _showImageUploadErrorDialog

    private val _showScreenErrorDialogTitle = SingleLiveEvent<Boolean>()
    val showScreenErrorDialogTitle: LiveData<Boolean> = _showScreenErrorDialogTitle

    fun init(
        showsRepository: ShowsRepository,
        userRepository: UserRepository,
        showCardUiMapper: ShowCardUiMapper,
    ) {
        this.userRepository = userRepository
        this.showsRepository = showsRepository
        viewModelScope.launch(Dispatchers.IO) {
            val shows = mutableListOf<ShowCardUi>()
            try {
                val domainShows = showsRepository.listShows()
                shows.addAll(showCardUiMapper.mapFromDomain(domainShows))
            } catch (_: Exception) {
                _showScreenErrorDialogTitle.value = true
            }
            _state.postValue(
                ShowUi(
                    this@ShowViewModel.userRepository.getSavedUser()?.avatarUrl ?: "",
                    this@ShowViewModel.userRepository.getUsername(),
                    shows = shows
                )
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
                val user = userRepository.setUserAvatar(photo)
                _state.value = _state.value?.copy(
                    userAvatarUrl = user.avatarUrl
                )
            } catch (e: Exception) {
                _showImageUploadErrorDialog.value = true
            }
        }
    }
}
