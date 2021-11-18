import main.kotlin.Utils
import main.kotlin.models.Arguments
import main.kotlin.models.CodeExecute
import main.kotlin.providers.AuthenticationProvider
import main.kotlin.providers.AuthorizeProvider
import main.kotlin.providers.DataBaseProvider
import kotlin.system.exitProcess

fun main(args: Array<String>){
    val authenticationProvider = AuthenticationProvider(DataBaseProvider())
    val authorizeProvider = AuthorizeProvider(DataBaseProvider())

    val arguments: Arguments = Utils.parseArguments(args)
    var exitCode: CodeExecute = CodeExecute.OK

    if (arguments.login != null && arguments.password != null){
        exitCode = authenticationProvider.authenticate(arguments.login, arguments.password)
    }

    if (arguments.role != null && arguments.resource != null){
        // Провести авторизацию
        exitCode = authorizeProvider.authorize(
            arguments.login.toString(),
            arguments.role,
            arguments.resource,
            arguments.ds,
            arguments.de,
            arguments.vol
        )
    }

    exitProcess(exitCode.statusCode)
}