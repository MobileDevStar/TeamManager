package com.itdev.teammanager.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Calendar
import java.util.Calendar.DAY_OF_YEAR

@Entity(tableName = "members")
data class Member(
    @PrimaryKey @ColumnInfo(name = "id") val memberId: String,
    val name: String,
    val description: String,
    val growZoneNumber: Int,
    val wateringInterval: Int = 7, // how often the plant should be watered, in days
    val imageUrl: String = ""
) {

    /**
     * Determines if the plant should be watered.  Returns true if [since]'s date > date of last
     * watering + watering Interval; false otherwise.
     */
    fun shouldBeCompleted(since: Calendar, lastCompletedDate: Calendar) =
        since > lastCompletedDate.apply { add(DAY_OF_YEAR, wateringInterval) }

    override fun toString() = name
}