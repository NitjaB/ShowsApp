package infinuma.android.shows.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import infinuma.android.shows.details.domain.models.Review

@Dao
interface ReviewDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(review : Review)

    @Query("SELECT * FROM review_table")
    fun getAllReviews(): List<Review>

    @Query("SELECT * FROM review_table where id = :id")
    fun getReviewById(id: String): Review

}