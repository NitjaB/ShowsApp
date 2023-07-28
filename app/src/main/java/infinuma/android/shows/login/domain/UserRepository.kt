package infinuma.android.shows.login.domain

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import infinuma.android.shows.R
import infinuma.android.shows.login.domain.mappers.UserMapper
import infinuma.android.shows.login.domain.models.User
import infinuma.android.shows.network.ShowRemoteApi
import infinuma.android.shows.utils.FileUtil
import java.io.ByteArrayOutputStream

class UserRepository(
    private val sharedPreferences: SharedPreferences,
    private val context: Context,
    private val showRemoteApi: ShowRemoteApi,
    private val userMapper: UserMapper,
) {
    companion object {
        private const val SHARED_PREFS_REMEMBER_ME = "sharedPrefsRememberMe"
        private const val SHARED_PREFS_USER_EMAIL = "sharedPrefsUserEmail"
        private const val SHARED_PREFS_USER_IMAGE_URL = "sharedPrefsUserImageUrl"
    }

    suspend fun login(
        email: String,
        password: String
    ) {
        val response = showRemoteApi.login(email, password)
        val user = userMapper.fromResources(response.user)
        saveUser(user)
    }

    fun isUserRemembered() = sharedPreferences.getBoolean(SHARED_PREFS_REMEMBER_ME, false)
    fun setRememberedUser(remember: Boolean) {
        with(sharedPreferences.edit()) {
            putBoolean(SHARED_PREFS_REMEMBER_ME, remember)
            apply()
        }
    }

    private fun saveUser(user: User) {
        with(sharedPreferences.edit()) {
            putString(SHARED_PREFS_USER_EMAIL, user.email)
            putString(SHARED_PREFS_USER_IMAGE_URL, user.avatarUrl)
            apply()
        }
    }

    fun getSavedUser(): User? {
        val email = sharedPreferences.getString(SHARED_PREFS_USER_EMAIL, null)
        return if (!email.isNullOrBlank()) {
            User(
                email = email,
                avatarUrl = sharedPreferences.getString(SHARED_PREFS_USER_IMAGE_URL, null) ?: ""
            )
        } else {
            null
        }
    }

    fun getUsername() =
        sharedPreferences.getString(SHARED_PREFS_USER_EMAIL, null)

    fun getUserAvatar(): Bitmap {
        val savedImage = FileUtil.getImageFile(context)
        return if (savedImage != null) {
            BitmapFactory.decodeFile(savedImage.path)
        } else {
            BitmapFactory.decodeResource(
                context.resources,
                R.drawable.ic_profile_picture
            )
        }
    }

    fun setUserAvatar(bitmap: Bitmap) {
        FileUtil.saveBitmap(context, bitmap)
    }

    fun deleteUserAvatar() {
        FileUtil.getImageFile(context)?.delete()
    }
}

fun FileUtil.saveBitmap(context: Context, bitmap: Bitmap) {
    val bytes = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, bytes);
    val imageFile = createImageFile(context)
    imageFile?.createNewFile()
    try {
        imageFile?.writeBytes(bytes.toByteArray())
        bytes.flush()
        bytes.close()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}