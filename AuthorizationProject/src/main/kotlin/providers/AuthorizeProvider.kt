package main.kotlin.providers

import main.kotlin.models.CodeExecute
import main.kotlin.models.RoleResourse
import main.kotlin.models.Roles
import main.kotlin.models.User
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

object AuthorizeProvider {

    val dataBase: DataBaseProvider = DataBaseProvider()

    fun authorize(login: String, password: String, role: String, resourse: String, ds: String?, de: String?, vol: String?): CodeExecute {

        if (!Roles.roleValidate(role)){
            return CodeExecute.UNKNOWN_ROLES
        }
        if (!isResourceAccess(login, role, resourse)){
            return CodeExecute.FORBIDDEN
        }

        if (ds != null && de != null && vol != null){
            if (!dateValidate(ds, de) || !volValidate(vol)) {
                return CodeExecute.INCORRECT_ACTIVITY
            }
        }

        return CodeExecute.OK
    }

    fun dateValidate(ds: String, de: String): Boolean {
        val format: String = "yyyy-MM-dd"
        val dateStart: LocalDate
        val dateEnd: LocalDate

        try{
            dateStart = LocalDate.parse(ds, DateTimeFormatter.ofPattern(format))
            dateEnd = LocalDate.parse(de, DateTimeFormatter.ofPattern(format))
        }
        catch (ex: DateTimeParseException){
            return false
        }

        return dateStart < dateEnd
    }

    fun volValidate(vol: String): Boolean {
        return vol.toIntOrNull() != null
    }

    /**
     * Метод для проверки доступа к ресурсу
     */
    fun isResourceAccess(login: String, role: String, resourse: String): Boolean {
        val userId = (dataBase.getUserByLogin(login) as User).id

        for (item in dataBase.getResourses()) {
            if (item.userId == userId && item.role.name == role && RoleResourse.isResourse(resourse, item.resource)) {
                return true
            }
        }
        return false
    }
}