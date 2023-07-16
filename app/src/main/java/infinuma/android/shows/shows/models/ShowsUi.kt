package infinuma.android.shows.shows.models

import androidx.annotation.DrawableRes

data class ShowsUi(
    val id: String,
    @DrawableRes val image: Int,
    val name: String,
    val description: String,
)
