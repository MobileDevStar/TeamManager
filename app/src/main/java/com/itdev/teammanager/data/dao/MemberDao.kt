package com.itdev.teammanager.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import com.itdev.teammanager.data.model.Member;

/**
 * The Data Access Object for the Member class.
 */
@Dao
interface MemberDao {
    @Query("SELECT * FROM members ORDER BY name")
    fun getMembers(): Flow<List<Member>>

    @Query("SELECT * FROM members WHERE growZoneNumber = :growZoneNumber ORDER BY name")
    fun getMembersWithGrowZoneNumber(growZoneNumber: Int): Flow<List<Member>>

    @Query("SELECT * FROM members WHERE id = :memberId")
    fun getMember(memberId: String): Flow<Member>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(members: List<Member>)
}
