package main.kotlin

import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import main.kotlin.models.Arguments
import java.security.MessageDigest

object Utils {

    fun encode(password: String, salt: String): String = getHash(getHash(password) + salt)

    private fun getHash(sourse: String): String = MessageDigest.getInstance("MD5").digest(sourse.toByteArray()).toStr()

    private fun ByteArray.toStr(): String = joinToString("") { "%02x".format(it) }

    fun parseArguments(args: Array<String>): Arguments {

        val parser = ArgParser("Authorization project")

        val login by parser.option(ArgType.String, shortName = "login", description = "Input login")
        val password by parser.option(ArgType.String, shortName = "password", description = "Input password")
        val role by parser.option(ArgType.String, shortName = "role", description = "Input role")
        val resource by parser.option(ArgType.String, shortName = "resource", description = "Input resource")
        val ds by parser.option(ArgType.String, shortName = "ds", description = "Input date start: YYYY-m-d")
        val de by parser.option(ArgType.String, shortName = "de", description = "Input date finish: YYYY-m-d")
        val vol by parser.option(ArgType.String, shortName = "vol", description = "Input number")

        parser.parse(args)

        return Arguments(login, password, role, resource, ds, de, vol)
    }

    fun isResource(resource: String, itemResource: String): Boolean {
        val resourceList = resource.split(".")
        val itemResourceList = itemResource.split(".")

        if (itemResourceList.count() > resourceList.count()) {
            return false
        }

        for (i in 0 until itemResourceList.count()) {
            if (itemResourceList[i] != resourceList[i]) {
                return false
            }
        }
        return true
    }
}