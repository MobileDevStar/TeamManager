package com.itdev.teammanager.data.repository

import androidx.lifecycle.asLiveData
import com.itdev.teammanager.data.dao.MemberDao
import com.itdev.teammanager.data.dao.TeamMemberDao
import com.itdev.teammanager.data.model.TeamMember
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository module for handling data operations.
 *
 * Collecting from the Flows in [MemberDao] is main-safe.  Room supports Coroutines and moves the
 * query execution off of the main thread.
 */
@Singleton
class TeamMemberRepository @Inject constructor(
    private val teamMemberDao: TeamMemberDao
) {

    suspend fun createTeamMember(memberId: String) {
        val teamMember = TeamMember(memberId)
        teamMemberDao.insertTeamMember(teamMember)
    }

    suspend fun removeTeamMember(teamMember: TeamMember) {
        teamMemberDao.deleteTeamMember(teamMember)
    }

    fun isJoined(memberId: String) = teamMemberDao.isJoined(memberId)

    fun getMemberedTeams() = teamMemberDao.getMemberedTeams()
}