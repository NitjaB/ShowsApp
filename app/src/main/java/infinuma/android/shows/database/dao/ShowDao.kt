package infinuma.android.shows.database.dao

import androidx.room.Dao
import androidx.room.Query
import infinuma.android.shows.shows.domain.models.ShowInfo

@Dao
interface ShowDao {

    @Query("SELECT * FROM show_table")
    fun getAllShows(): List<ShowInfo>

    @Query("SELECT * FROM show_table where id = :id")
    fun getShowById(id: String): ShowInfo
}