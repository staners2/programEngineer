package main.kotlin.models

data class RoleResource(
    val role: Roles,
    val resource: String,
    val userId: Int = 0
)