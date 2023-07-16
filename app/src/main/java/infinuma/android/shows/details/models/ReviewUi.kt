package infinuma.android.shows.details.models

import androidx.annotation.DrawableRes

data class ReviewUi(
    @DrawableRes val avatar: Int,
    val username: String,
    val starGrade: Int,
    val review: String?
)
