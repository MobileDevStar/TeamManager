package com.itdev.teammanager.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.itdev.teammanager.data.model.MemberAndTeamMembers
import com.itdev.teammanager.data.model.MemberPhoto
import com.itdev.teammanager.data.repository.PhotoRepository
import com.itdev.teammanager.data.repository.TeamMemberRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class TeamMemberListViewModel @Inject internal  constructor(
    teamMemberRepository: TeamMemberRepository
) : ViewModel() {
    val teamMembers: LiveData<List<MemberAndTeamMembers>> = teamMemberRepository.getMemberedTeams().asLiveData()
}