package com.itdev.teammanager.data.model

import com.google.gson.annotations.SerializedName

data class MemberPhoto (
    @field:SerializedName("id") val id: String,
    @field:SerializedName("urls") val urls: MemberPhotoUrls,
    @field:SerializedName("user") val user: MemberInfo
)