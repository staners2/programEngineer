package main.kotlin.providers

import main.kotlin.Utils
import main.kotlin.models.CodeExecute
import main.kotlin.models.Roles
import main.kotlin.models.User
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

object AuthorizeProvider {

    private val dataBase: DataBaseProvider = DataBaseProvider()

    fun authorize(login: String, role: String, resource: String, ds: String?, de: String?, vol: String?): CodeExecute {

        if (!Roles.roleValidate(role)){
            return CodeExecute.UNKNOWN_ROLES
        }
        if (!isResourceAccess(login, role, resource)){
            return CodeExecute.FORBIDDEN
        }

        if (ds != null && de != null && vol != null){
            if (!dateValidate(ds, de) || !volValidate(vol)) {
                return CodeExecute.INCORRECT_ACTIVITY
            }
        }

        return CodeExecute.OK
    }

    private fun dateValidate(ds: String, de: String): Boolean {
        val format = "yyyy-MM-dd"

        return try{
            val dateStart = LocalDate.parse(ds, DateTimeFormatter.ofPattern(format))
            val dateEnd = LocalDate.parse(de, DateTimeFormatter.ofPattern(format))

            dateStart < dateEnd
        } catch (ex: DateTimeParseException){
            false
        }
    }

    private fun volValidate(vol: String): Boolean {
        return vol.toIntOrNull() != null
    }

    /**
     * Метод для проверки доступа к ресурсу
     */
    private fun isResourceAccess(login: String, role: String, resourse: String): Boolean {
        val userId = (dataBase.getUserByLogin(login) as User).id

        for (item in dataBase.getResourses()) {
            if (item.userId == userId && item.role.name == role && Utils.isResource(resourse, item.resource)) {
                return true
            }
        }
        return false
    }
}