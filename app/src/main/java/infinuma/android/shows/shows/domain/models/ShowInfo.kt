package infinuma.android.shows.shows.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("show_table")
data class ShowInfo(
    @PrimaryKey
    val id: String,
    val averageRating: Float?,
    val description: String,
    val imageUrl: String,
    val numberOfReviews: Int,
    val title: String,
    val hasReviews: Boolean? = null
)
