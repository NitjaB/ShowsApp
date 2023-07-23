package infinuma.android.shows.shows.models

import android.graphics.Bitmap

data class ShowUi(
    val userAvatar: Bitmap? = null,
    val userEmail: String? = null,
    val shows: List<ShowCardUi> = listOf(),
)
