package main.kotlin.providers

import main.kotlin.Utils
import main.kotlin.models.CodeExecute

object AuthenticationProvider {

    private val dataBase: DataBaseProvider = DataBaseProvider()

    fun authenticate(login: String, password: String): CodeExecute {

        if (!loginValidate(login)){
            return CodeExecute.NOT_FORMAT_LOGIN
        }
        if (!dataBase.hasLogin(login)){
            return CodeExecute.NOT_LOGIN
        }
        if (!passwordValidate(login, password)){
            return CodeExecute.NOT_PASSWORD
        }

        return CodeExecute.OK
    }

    private fun loginValidate(login: String): Boolean {
        return (Regex("^[a-zA-z0-9]{0,20}$").matches(login))
    }

    private fun passwordValidate(login: String, password: String): Boolean {
        val salt = dataBase.getSaltByLogin(login)
        val resultPassword = dataBase.getPasswordByLogin(login)

        return Utils.encode(password, salt) == resultPassword
    }
}