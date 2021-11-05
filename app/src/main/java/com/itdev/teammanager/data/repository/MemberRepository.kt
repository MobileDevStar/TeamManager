package com.itdev.teammanager.data.repository

import androidx.lifecycle.asLiveData
import com.itdev.teammanager.data.dao.MemberDao
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository module for handling data operations.
 *
 * Collecting from the Flows in [MemberDao] is main-safe.  Room supports Coroutines and moves the
 * query execution off of the main thread.
 */
@Singleton
class MemberRepository @Inject constructor(private val memberDao: MemberDao) {

    fun getMembers() = memberDao.getMembers()

    fun getMember(memberId: String) = memberDao.getMember(memberId)

    fun getMembersWithGrowZoneNumber(growZoneNumber: Int) =
        memberDao.getMembersWithGrowZoneNumber(growZoneNumber)
}