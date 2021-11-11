package com.itdev.teammanager.viewmodels

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.itdev.teammanager.BuildConfig
import com.itdev.teammanager.data.repository.MemberRepository
import com.itdev.teammanager.data.repository.TeamMemberRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * The ViewModel used in [MemberDetailFragment].
 */
@HiltViewModel
class MemberDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    memberRepository: MemberRepository,
    private val teamMemberRepository: TeamMemberRepository
) : ViewModel() {

    val memberId: String = savedStateHandle.get<String>(MEMBER_ID_SAVED_STATE_KEY)!!

    val isJoined = teamMemberRepository.isJoined(memberId).asLiveData()
    val member = memberRepository.getMember(memberId).asLiveData()

    fun addMemberToTeam() {
        viewModelScope.launch {
            teamMemberRepository.createTeamMember(memberId)
        }
    }

    fun hasValidUnsplashKey() = (BuildConfig.UNSPLASH_ACCESS_KEY != "null")

    fun getMemberName() = member.toString()

    companion object {
        private const val TAG = "Member Detail Fragment"
        private const val MEMBER_ID_SAVED_STATE_KEY = "memberId"
    }
}