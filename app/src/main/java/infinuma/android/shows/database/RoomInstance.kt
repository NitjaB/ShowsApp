package infinuma.android.shows.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import infinuma.android.shows.database.dao.ReviewDao
import infinuma.android.shows.database.dao.ShowDao
import infinuma.android.shows.details.domain.models.Review
import infinuma.android.shows.shows.domain.models.ShowInfo

object RoomInstance {

    private lateinit var database: ShowDatabase

    fun instantiate(context: Context) {
        database = ShowDatabase.getDatabase(context)
    }

    fun get() = database

}

@Database(entities = [Review::class, ShowInfo::class], version = 1)
abstract class ShowDatabase : RoomDatabase() {

    abstract fun reviewDao(): ReviewDao

    abstract fun showDao(): ShowDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: ShowDatabase? = null

        fun getDatabase(context: Context): ShowDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ShowDatabase::class.java,
                    "show_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
