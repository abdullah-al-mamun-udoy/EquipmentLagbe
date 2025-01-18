package com.metamamun.equipment.data.model

data class UserProfile(
    val id: String = "",
    val username: String = "",
    val profilePicture: String = "",
    val email: String = "",
    val age: String = "",
    val sex: String = "",
    val height: String = "",
    val weight: String = "",
    val weightGoal: String = "",
    val registrationCompleted: Boolean = false
)
