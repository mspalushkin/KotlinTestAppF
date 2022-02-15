/*
 * 02.06.2021
 * @author Maksim Palushkin
 */

package com.palushkin.data.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.*

@Dao
interface UserDao {

    @Query("select * from databaseEntity")
    fun getUsers(): LiveData<List<DatabaseEntity>>

    @Query("select * from databaseEntity")
    fun getUsersFlow(): Flow<List<DatabaseEntity>>
    // @Query("SELECT * from plants WHERE growZoneNumber = :growZoneNumber ORDER BY name")
    // fun getPlantsWithGrowZoneNumberFlow(growZoneNumber: Int): Flow<List<Plant>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg videos: DatabaseEntity)
}

@Database(
    entities = [
        DatabaseEntity::class
    ], version = 1
)
abstract class EntityDatabase : RoomDatabase() {
    abstract val userDao: UserDao
}

private lateinit var INSTANCE: EntityDatabase

fun getDatabase(context: Context): EntityDatabase {
    synchronized(EntityDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                EntityDatabase::class.java,
                "u"
            ).build()
        }
    }
    return INSTANCE
}