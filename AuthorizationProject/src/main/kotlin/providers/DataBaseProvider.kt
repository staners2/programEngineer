package main.kotlin.providers

import main.kotlin.models.RoleResource
import main.kotlin.models.User
import main.kotlin.soursedata.SourseData

class DataBaseProvider {

    fun getUserByLogin(login: String): User? {
        return SourseData.users.find { it.login == login }
    }

    fun getPasswordByLogin(login: String): String {
        return (getUserByLogin(login) as User).pass
    }

    fun getSaltByLogin(login: String): String {
        return (getUserByLogin(login) as User).salt
    }

    fun hasLogin(login: String): Boolean {
        return getUserByLogin(login) != null
    }

    fun getResourses(): List<RoleResource> {
        return SourseData.roleResours
    }
}