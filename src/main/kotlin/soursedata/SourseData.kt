package main.kotlin.soursedata

import main.kotlin.models.User
import main.kotlin.models.RoleResource
import main.kotlin.models.Roles

object SourseData {
    val roleResours: List<RoleResource> = listOf(
        RoleResource(Roles.READ, "A", 0),
        RoleResource(Roles.READ, "B", 0),
        RoleResource(Roles.READ, "C", 0),
        RoleResource(Roles.WRITE, "A", 0),
        RoleResource(Roles.WRITE, "B", 0),
        RoleResource(Roles.WRITE, "C", 0),
        RoleResource(Roles.EXECUTE, "A", 0),
        RoleResource(Roles.EXECUTE, "B", 0),
        RoleResource(Roles.EXECUTE, "C", 0),
        RoleResource(Roles.READ, "A", 1),
        RoleResource(Roles.EXECUTE, "A.B", 1),
        RoleResource(Roles.WRITE, "XY.UV.ABCDEFGHIJ", 1),
        RoleResource(Roles.READ, "A", 2),
        RoleResource(Roles.WRITE, "A.B", 2),
        RoleResource(Roles.EXECUTE, "A.B.C", 3)
    )
    
    val users: List<User> = listOf(
        User(0, "admin", "2b0f98d3c29b6eff9634917feef9fe6b", "salt"), // 00000
        User(1, "user1", "319027b1c55986eb8a492273168964f9", "salt1"), // 11111
        User(2, "user2", "97d1158adbaf32d93eb37451fddac594", "salt2"), // 22222
        User(3, "user3", "32e703cc91abd2dd704495119641fb96", "salt3"), // 33333
    )
}