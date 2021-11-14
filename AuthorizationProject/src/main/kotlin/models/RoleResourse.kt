package main.kotlin.models

data class RoleResourse(
    val role: Roles,
    val resource: String,
    val userId: Int = 0,
)
