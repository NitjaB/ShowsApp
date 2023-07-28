package infinuma.android.shows.shows.models

data class ShowUi(
    val userAvatarUrl: String? = null,
    val userEmail: String? = null,
    val shows: List<ShowCardUi> = listOf(),
)
