package com.itdev.teammanager.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.itdev.teammanager.data.dao.MemberDao
import com.itdev.teammanager.data.dao.TeamMemberDao
import com.itdev.teammanager.data.model.Member
import com.itdev.teammanager.data.model.TeamMember
import com.itdev.teammanager.utilities.DATABASE_NAME
import com.itdev.teammanager.utilities.MEMBER_DATA_FILENAME
import com.itdev.teammanager.workers.SeedDatabaseWorker
import com.itdev.teammanager.workers.SeedDatabaseWorker.Companion.KEY_FILENAME

/**
 * The Room database for this app
 */
@Database(entities = [TeamMember::class, Member::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun teamMemberDao(): TeamMemberDao
    abstract fun memberDao(): MemberDao

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // Create and pre-populate the database. See this article for more details:
        // https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .addCallback(
                    object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>()
                                .setInputData(workDataOf(KEY_FILENAME to MEMBER_DATA_FILENAME))
                                .build()
                            WorkManager.getInstance(context).enqueue(request)
                        }
                    }
                )
                .build()
        }
    }
}