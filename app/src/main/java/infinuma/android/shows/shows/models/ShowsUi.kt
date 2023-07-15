package infinuma.android.shows.shows.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class ShowsUi(
    val id: Int,
    @DrawableRes val image: Int,
    val title: String,
    val description: String,
)
