package main.kotlin.soursedata

import main.kotlin.models.User
import main.kotlin.models.RoleResourse
import main.kotlin.models.Roles

object SourseData {
    public val roleResourses: List<RoleResourse> = listOf(
        RoleResourse(
            Roles.READ,
            "A",
            0
        ),
        RoleResourse(
            Roles.READ,
            "B",
            0
        ),
        RoleResourse(
            Roles.READ,
            "C",
            0
        ),
        RoleResourse(
            Roles.WRITE,
            "A",
            0
        ),
        RoleResourse(
            Roles.WRITE,
            "B",
            0
        ),
        RoleResourse(
            Roles.WRITE,
            "C",
            0
        ),
        RoleResourse(
            Roles.EXECUTE,
            "A",
            0
        ),
        RoleResourse(
            Roles.EXECUTE,
            "B",
            0
        ),
        RoleResourse(
            Roles.EXECUTE,
            "C",
            0
        ),
        //endregion
        //region user
        RoleResourse(
            Roles.READ,
            "A",
            1
        ),
        RoleResourse(
            Roles.EXECUTE,
            "A.B",
            1
        ),
        RoleResourse(
            Roles.WRITE,
            "XY.UV.ABCDEFGHIJ",
            1
        ),
        RoleResourse(
            Roles.READ,
            "A",
            2
        ),
        RoleResourse(
            Roles.WRITE,
            "A.B",
            2
        ),
        RoleResourse(
            Roles.EXECUTE,
            "A.B.C",
            3
        )
    )
    
    public val users: List<User> = listOf(
        User(0, "admin", "2b0f98d3c29b6eff9634917feef9fe6b", "salt"), // 00000
        User(1, "user1", "319027b1c55986eb8a492273168964f9", "salt1"), // 11111
        User(2, "user2", "97d1158adbaf32d93eb37451fddac594", "salt2"), // 22222
        User(3, "user3", "32e703cc91abd2dd704495119641fb96", "salt3"), // 33333
    )
}