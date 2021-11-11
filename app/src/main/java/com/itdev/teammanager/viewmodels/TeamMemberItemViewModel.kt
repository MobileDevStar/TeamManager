package com.itdev.teammanager.viewmodels

import com.itdev.teammanager.data.model.MemberAndTeamMembers
import java.text.SimpleDateFormat
import java.util.Locale

class TeamMemberItemViewModel (
    members: MemberAndTeamMembers
) {
    private val member = checkNotNull(members.member)
    private val teamMember = members.teamMembers[0]

    val completedDateString: String = dateFormat.format(teamMember.lastCompletedDate.time)

    val taskInterval
        get() = member.wateringInterval

    val imageUrl
        get() = member.imageUrl

    val memberName
        get() = member.name
    val joinedDateString: String = dateFormat.format(teamMember.joinedDate.time)

    val memberId
        get() = member.memberId

    companion object {
        private val dateFormat = SimpleDateFormat("MMM d, yyyy", Locale.US)
    }
}