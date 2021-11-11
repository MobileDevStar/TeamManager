package com.itdev.teammanager.data.model

import androidx.room.*
import java.util.Calendar
import java.util.Calendar.getInstance

@Entity(
    tableName = "team_members",
    foreignKeys = [
        ForeignKey(entity = Member::class, parentColumns = ["id"], childColumns = ["member_id"])
    ],
    indices = [Index("member_id")]
)
data class TeamMember(
    @ColumnInfo(name = "member_id") val memberId: String,
    @ColumnInfo(name = "joined_date") val joinedDate: Calendar = getInstance(),
    @ColumnInfo(name = "last_completed_date") val lastCompletedDate: Calendar = getInstance()
) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var teamMemberId: Long = 0
}