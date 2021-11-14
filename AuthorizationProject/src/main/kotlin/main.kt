import main.kotlin.models.Arguments
import main.kotlin.models.CodeExecute
import main.kotlin.providers.AuthenticationProvider
import main.kotlin.providers.AuthorizeProvider

fun main(args: Array<String>){
    val arguments: Arguments = Arguments.parseArguments(args)
    var exitCode: CodeExecute = CodeExecute.OK

    if (arguments.login != null && arguments.password != null){
        exitCode = AuthenticationProvider.authenticate(arguments.login, arguments.password)

        if (arguments.role != null && arguments.resourse != null){
            // Провести авторизацию
            exitCode = AuthorizeProvider.authorize(arguments.login, arguments.password, arguments.role, arguments.resourse, arguments.ds, arguments.de, arguments.vol)
            // TODO ...
        }
    }

    System.exit(exitCode.statusCode)
}