package infinuma.android.shows.details.models

import android.graphics.Bitmap

data class ReviewUi(
    val avatar: Bitmap,
    val username: String,
    val starGrade: Int,
    val review: String?
)
