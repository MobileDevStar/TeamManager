package com.itdev.teammanager.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class MemberAndTeamMembers (
    @Embedded
    val member: Member,

    @Relation(parentColumn = "id", entityColumn = "member_id")
    val teamMembers: List<TeamMember> = emptyList()
)