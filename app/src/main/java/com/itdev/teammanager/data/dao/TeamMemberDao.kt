package com.itdev.teammanager.data.dao

import androidx.room.*
import com.itdev.teammanager.data.model.MemberAndTeamMembers
import com.itdev.teammanager.data.model.TeamMember
import kotlinx.coroutines.flow.Flow

/**
 * The Data Access Object for the [TeamMember] class.
 */
@Dao
interface TeamMemberDao {
    @Query("SELECT * FROM team_members")
    fun getTeamMembers(): Flow<List<TeamMember>>

    @Query("SELECT EXISTS(SELECT 1 FROM team_members WHERE member_id = :memberId LIMIT 1)")
    fun isJoined(memberId: String): Flow<Boolean>


    /**
     * This query will tell Room to query both the [Member] and [TeamMember] tables and handle
     * the object mapping.
     */
    @Transaction
    @Query("SELECT * FROM members WHERE id IN (SELECT DISTINCT(member_id) FROM team_members)")
    fun getMemberedTeams(): Flow<List<MemberAndTeamMembers>>

    @Insert
    suspend fun insertTeamMember(teamMember: TeamMember): Long

    @Delete
    suspend fun deleteTeamMember(teamMember: TeamMember)
}