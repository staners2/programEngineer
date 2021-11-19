package main.kotlin.soursedata

import main.kotlin.models.User
import main.kotlin.models.RoleResource
import main.kotlin.models.Roles

object SourseData {
    val roleResours: List<RoleResource> = listOf(
        RoleResource(Roles.READ, "A", 0),
        RoleResource(Roles.WRITE, "A.B", 0)
    )
    
    val users: List<User> = listOf(
        User(0, "admin", "2b0f98d3c29b6eff9634917feef9fe6b", "salt"), // 00000
        User(1, "user1", "319027b1c55986eb8a492273168964f9", "salt1") // 11111
    )
}