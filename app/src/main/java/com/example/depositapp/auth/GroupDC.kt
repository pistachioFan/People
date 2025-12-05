package com.example.depositapp.auth

import kotlinx.serialization.Serializable

@Serializable
data class GroupDC(
    val groupId: Int,
    val groupName: String
)
