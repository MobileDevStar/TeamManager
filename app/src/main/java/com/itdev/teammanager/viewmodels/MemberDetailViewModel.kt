package com.itdev.teammanager.viewmodels

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.itdev.teammanager.BuildConfig
import com.itdev.teammanager.data.repository.MemberRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * The ViewModel used in [MemberDetailFragment].
 */
@HiltViewModel
class MemberDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    memberRepository: MemberRepository
) : ViewModel() {

    val memberId: String = savedStateHandle.get<String>(MEMBER_ID_SAVED_STATE_KEY)!!

    val member = memberRepository.getMember(memberId).asLiveData()

    fun addMemberToTeam() {
        Log.d(TAG, member.toString())
    }

    fun hasValidUnsplashKey() = (BuildConfig.UNSPLASH_ACCESS_KEY != "null")

    fun getMemberName() = member.toString()

    companion object {
        private const val TAG = "Member Detail Fragment"
        private const val MEMBER_ID_SAVED_STATE_KEY = "memberId"
    }
}