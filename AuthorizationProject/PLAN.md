# Структура объектов

* Создать объект **Пользователь**
```kotlin
data class User(
    val id: Int,
    val login: String,
    val pass: String,
    val salt: String,

)
```

* Создать перечисление **Ролей**
```kotlin
enum class Roles() {
    READ,
    WRITE,
    EXECUTE
}
```

* Создать объект **РольРесурс**
```kotlin
// {user resourse role}
class RoleResourses {
    TODO ...
}
```

* Создать объект **Аргумент**
```kotlin
data class Arguments(
    val login: String?,
    val password: String?,
    val role: String?,
    val resourse: String?,
    val ds: String?,
    val de: String?,
    val vol: String?
)
```

* Создать объект **КодСтатус**
```kotlin
enum class CodeExecute(val statusCode: Int){
    OK(0),
    HELP(1),
    NOT_FORMAT_LOGIN(2),
    NOT_LOGIN(3),
    NOT_PASSWORD(4),
    UNKNOWN_ROLES(5),
    FORBIDDEN(6),
    INCORRECT_ACTIVITY(7)
}
```

* Создать объект **БазаДанныхПровайдер**
```kotlin
import SourseData.*

class DataBaseProvider(val sourseData: SourseData) {
    
}
```

* Создать объект **ИсточникДанных**
```kotlin
package SourseData

class SourseData {
    public val roleResourses: List<RoleResourses> = listOf(...)
    public val users: List<User> = listOf(...)
}
```

* Создать объект **АутентификацииПровайдер**
```kotlin
// Для аутентификации (логин + pass)
class AuthenticationProvider {

    companion object {

    }
}
```

* Создать объект **АвторизацияПровайдер**
```kotlin
import java.time.LocalDate
import java.time.format.DateTimeFormatter

// Для авторизации (user + resourse)
class AuthorizeProvider {
    TODO ...
}
```



* Создать объект **Утилит**
```kotlin
import java.security.MessageDigest
import kotlin.random.Random
import kotlinx.cli.*

class Utils {
    companion object {

    }
}
```


# Методы объектов
## Объект: **БазаДанныхПровайдер** 

* Метод: Возвращающий объект пользователя по логину
```kotlin
fun getUserByLogin(login: String): User? {
    return sourseData.users.find { it.login == login }
}
```

* Метод: Возвращающий пароль по логину
```kotlin
fun getPasswordByLogin(login: String): String {
    return getUserByLogin(login)!!.pass
}
```

* Метод: Возвращающий соль по логину
```kotlin
fun getSaltByLogin(login: String): String {  
    return getUserByLogin(login)!!.salt
}
```

* Метод: Проверяющий наличие такого логина в БД
```kotlin
fun hasLogin(login: String): Boolean {
    return getUserByLogin(login) != null
}
```

## Объект: **АутентификацииПровайдер** 

* Метод: Начать Аутентификацию
```kotlin
fun authenticate(login: String, password: String): CodeExecute {
    if (!loginValidate(login)){
        return CodeExecute.NOT_FORMAT_LOGIN.statusCode
    }
    if (!DataBaseProvider.hasLogin(login)){
        return CodeExecute.NOT_LOGIN.statusCode
    }
    if (!userValidate(login, password)){
        return CodeExecute.NOT_PASSWORD.statusCode
    }

    return CodeExecute.OK

}
```

* Метод: Проверяющий валидный ли логин
```kotlin
fun loginValidate(login: String): Boolean {
    return (Regex("^[a-zA-z0-9]{0,20}$").mathes(login))
}
```

* Метод: Проверяющий совпадение паролей
```kotlin
fun userValidate(login: String, password: String): Boolean {
    val salt = DataBaseProvider.getSaltByLogin(login)
    val resultPassword = DataBaseProvider.getPasswordByLogin(login)

    return Utils.encode(argPass, salt) == resultPassword
}
```

* Метод: TODO ...
```kotlin

```

## Объект: **АвторизацияПровайдер** 

* Метод: Авторизации
```kotlin
fun authorize(login: String, password: String, role: String, resourse: String, ds: String?, de: String?, vol: String?): CodeExecute {
    if (!Roles.roleValidate(role)){
        return CodeExecute.UNKNOWN_ROLES.statusCode
    }
    if (!dostup()){
        return CodeExecute.FORBIDDEN.statusCode
    }
    if (ds != null && de != null && vol != null){
        if (dateValidate(ds, de) || volValidate(vol)){
            return CodeExecute.INCORRECT_ACTIVITY.statusCode
        }
    }
    return CodeExecute.OK.statuscode
}
```

* Метод: Валидация даты
```kotlin
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
```


* Метод: Валидация объема
```kotlin
fun volValidate(vol: String): Boolean {
    return vol.toIntOrNull() != null 
}
```

* Метод: Имеет ли пользователь доступ к ресурсу
```kotlin
fun dostup(): Boolean {

}
```

* Метод: TODO ...
```kotlin

```


## Объект: **Ролей**

* Метод: Валидация роли
```kotlin
companion object {
    fun roleValidate(role: String): Boolean {
        return Roles.values().any { it.name == role }
    }
}
```

## Объект: **Утилит** 

* Метод: Генерирует соль случайно
```kotlin
fun genearateSalt(): String = Random.nextBytes(32).joinToString("") { "%02x".format(it) }
```

* Метод: Шифрует пароль
```kotlin
fun encode(password: String, salt: String): String = getHash(getHash(password) + salt)
```      

* Метод: Шифрует строку MD5
```kotlin
fun getHash(sourse: String): String = MessageDigest.getInstance("MD5").digest(sourse.toByteArray()).joinToString("") { "%02x".format(it) }
```  

* Метод: Переводит в строку
```kotlin
// TODO
fun ByteArray.toStr(): String = MessageDigest.getInstance("MD5").digest(sourse.toByteArray()).joinToString("") { "%02x".format(it) }
``` 

* Метод: Парсит строку и возвращает объект Аргументов
```kotlin
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
```


# Тестовые данные

## Список: **Пользователей**
```kotlin

1. admin(login = admin, password = 0000)
2. user(login = user, password = 1111)

```

## Список: **Ресурсов**
```kotlin

1. admin READ A
2. admin READ B
3. admin

```


# Тестовые сценарии
```kotlin
// Наличие справки
java -jar "main.jar" -h
// Аутентификация
java -jar "main.jar" -login user -pass 1111
// Аутентификация
java -jar "main.jar" -pass 1111 -login user
// Логин не проходит валидацию  (> 3 символов)
java -jar "main.jar" -login use -pass 1111
// Такой аккаунт не существует
java -jar "main.jar" -login 1234 -pass 1234


// TODO ...
//
java -jar "main.jar" -login user -pass 11111 -role READ -res A
//
java -jar "main.jar" -login user -pass zzz -role qwe -res A
//
java -jar "main.jar" -login q -pass ytrewq -role EXECUTE -res A
//
java -jar "main.jar" -login admin -pass 0000 -role READ -res A.B
//
java -jar "main.jar" -login admin -pass 0000 -role WRITE -res A.B.C
//
java -jar "main.jar" -login user -pass zzz -role qwe -res A.B.C
//
java -jar "main.jar" -login user -pass zzz -role WRITE -res A.B
java -jar "main.jar" -login user -pass zzz -role READ -res A.B -ds 2020-01-11 -de 2020-01-12 -vol 10
java -jar "main.jar" -login user -pass zzz -role READ -res A.B -ds 2020.01.11 -de 2020.01.12 -vol 10
java -jar "main.jar" -login user -pass zzz -role READ -res A.B -ds 2020-01-1 -de 2020-01-60 -vol 10
java -jar "main.jar" -login user -pass zzz -role READ -res A.B -ds 2020-01-11 -de 2020-10-12 -vol hgh

```


# Точка входа в приложение
```kotlin

fun main(args: Array<String>){
    val arguments: Arguments = Urils.parseArguments(args)
    val exitCode: Int = 0

    if (arguments.login != null && arguments.password != null){
        exitCode = AuthenticationProvider.authenticate(arguments.login, arguments.password)

        if (arguments.role != null && arguments.resourse != null){
            // Провести авторизацию
            exitCode = AuthorizeProvider.authorize(arguments.login, arguments.password, arguments.role, arguments.resourse, arguments.ds, arguments.de, arguments.vol)
            // TODO ...
        }
    }
    
    System.exit(exitCode)
}


```



