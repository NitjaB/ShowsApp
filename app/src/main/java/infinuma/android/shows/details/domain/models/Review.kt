package infinuma.android.shows.details.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("review_table")
data class Review(
    @PrimaryKey
    val id: String,
    val avatarUrl: String,
    val username: String,
    val starGrade: Int,
    val review: String?,
)
