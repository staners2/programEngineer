package main.kotlin.models

import kotlinx.cli.*

data class Arguments(
    val login: String?,
    val password: String?,
    val role: String?,
    val resourse: String?,
    val ds: String?,
    val de: String?,
    val vol: String?
) {
    companion object {
        fun parseArguments(args: Array<String>): Arguments {

            val parser = ArgParser("Authorization project")

            val login by parser.option(ArgType.String, shortName = "login", description = "Input login")
            val password by parser.option(ArgType.String, shortName = "password", description = "Input password")
            val role by parser.option(ArgType.String, shortName = "role", description = "Input role")
            val resourse by parser.option(ArgType.String, shortName = "resourse", description = "Input resource")
            val ds by parser.option(ArgType.String, shortName = "ds", description = "Input date start: YYYY-m-d")
            val de by parser.option(ArgType.String, shortName = "de", description = "Input date finish: YYYY-m-d")
            val vol by parser.option(ArgType.String, shortName = "vol", description = "Input number")

            parser.parse(args)

            return Arguments(login, password, role, resourse, ds, de, vol)

        }
    }
}
