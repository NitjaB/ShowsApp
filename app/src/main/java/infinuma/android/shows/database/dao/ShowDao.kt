package infinuma.android.shows.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import infinuma.android.shows.shows.domain.models.ShowInfo

@Dao
interface ShowDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(showInfo: ShowInfo)

    @Query("SELECT * FROM show_table")
    fun getAllShows(): List<ShowInfo>

    @Query("SELECT * FROM show_table where id = :id")
    fun getShowById(id: String): ShowInfo

    @Update(entity = ShowInfo::class)
    fun updateShow(showInfo: ShowInfo)
}