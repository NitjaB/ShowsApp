package infinuma.android.shows.details.domain.models

data class Review(
    val avatarUrl: String,
    val username: String,
    val starGrade: Int,
    val review: String?
)
