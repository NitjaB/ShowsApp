package infinuma.android.shows.login.domain

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import infinuma.android.shows.R
import infinuma.android.shows.utils.FileUtil
import java.io.ByteArrayOutputStream

class UserRepository(
    private val sharedPreferences: SharedPreferences,
    private val context: Context,
) {
    companion object {
        private const val SHARED_PREFS_REMEMBER_ME = "sharedPrefsRememberMe"
        private const val SHARED_PREFS_USER_EMAIL = "sharedPrefsUserEmail"
    }

    fun isUserRemembered() = sharedPreferences.getBoolean(SHARED_PREFS_REMEMBER_ME, false)
    fun setRememberedUser(remember: Boolean) {
        with(sharedPreferences.edit()) {
            putBoolean(SHARED_PREFS_REMEMBER_ME, remember)
            apply()
        }
    }

    fun saveUsername(username: String?) {
        with(sharedPreferences.edit()) {
            putString(SHARED_PREFS_USER_EMAIL, username)
            apply()
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