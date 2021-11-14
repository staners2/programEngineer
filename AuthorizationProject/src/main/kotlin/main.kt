import main.kotlin.models.Arguments
import main.kotlin.models.CodeExecute
import main.kotlin.providers.AuthenticationProvider
import main.kotlin.providers.AuthorizeProvider
import kotlin.system.exitProcess

fun main(args: Array<String>){
    val arguments: Arguments = Arguments.parseArguments(args)
    var exitCode: CodeExecute = CodeExecute.OK

    if (arguments.login != null && arguments.password != null){
        exitCode = AuthenticationProvider.authenticate(arguments.login, arguments.password)

        if (arguments.role != null && arguments.resource != null){
            // Провести авторизацию
            exitCode = AuthorizeProvider.authorize(
                arguments.login,
                arguments.role,
                arguments.resource,
                arguments.ds,
                arguments.de,
                arguments.vol
            )
            // TODO ...
        }
    }

    exitProcess(exitCode.statusCode)
}