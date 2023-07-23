package infinuma.android.shows.shows.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import infinuma.android.shows.login.domain.UserRepository
import infinuma.android.shows.shows.data.ShowsRepository
import infinuma.android.shows.shows.models.ShowUi

class ShowViewModel : ViewModel() {

    private lateinit var userRepository: UserRepository
    private lateinit var showsRepository: ShowsRepository

    private val _state = MutableLiveData(ShowUi())
    val state: LiveData<ShowUi> = _state

    fun init(
        showsRepository: ShowsRepository,
        userRepository: UserRepository
    ) {
        this.userRepository = userRepository
        this.showsRepository = showsRepository
        _state.value = ShowUi(
            this.userRepository.getUserAvatar(),
            this.userRepository.getUsername(),
            this.showsRepository.getShows()
        )
    }

    fun hideShows() {
        _state.value = _state.value?.copy(
            shows = listOf()
        )
    }

    fun showShows() {
        _state.value = _state.value?.copy(
            shows = showsRepository.getShows()
        )
    }

    fun logout() {
        userRepository.setRememberedUser(false)
        userRepository.deleteUserAvatar()
    }

    fun changeProfilePicture(photo: Bitmap) {
        userRepository.setUserAvatar(photo)
        _state.value = _state.value?.copy(
            userAvatar = photo
        )
    }
}
