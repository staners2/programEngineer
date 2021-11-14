package main.kotlin.providers

import main.kotlin.models.CodeExecute

object AuthenticationProvider {

    fun authenticate(login: String, password: String): CodeExecute {
        if (!loginValidate(login)){
            return CodeExecute.NOT_FORMAT_LOGIN
        }
        if (!DataBaseProvider.hasLogin(login)){
            return CodeExecute.NOT_LOGIN
        }
        if (!passwordValidate(login, password)){
            return CodeExecute.NOT_PASSWORD
        }

        return CodeExecute.OK
    }

    fun loginValidate(login: String): Boolean {
        return (Regex("^[a-zA-z0-9]{0,20}$").matches(login))
    }

    fun passwordValidate(login: String, password: String): Boolean {
        val salt = DataBaseProvider.getSaltByLogin(login)
        val resultPassword = DataBaseProvider.getPasswordByLogin(login)

        return Utils.encode(password, salt) == resultPassword
    }
}