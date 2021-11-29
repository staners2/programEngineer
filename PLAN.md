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
data class RoleResource(
    val role: Roles,
    val resource: String,
    val userId: Int = 0
)
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
class DataBaseProvider(val sourseData: SourseData) {}
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
class AuthorizeProvider { }
```

* Создать объект **Утилит**
```kotlin
import java.security.MessageDigest
import kotlin.random.Random
import kotlinx.cli.*
class Utils { }
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


## Объект: **АвторизацияПровайдер** 

* Метод: Авторизации
```kotlin
fun authorize(login: String, password: String, role: String, resourse: String, ds: String?, de: String?, vol: String?): CodeExecute {
    if (!Roles.roleValidate(role)){
        return CodeExecute.UNKNOWN_ROLES.statusCode
    }
    if (!isResourceAccess(login, role, resource)){
        return CodeExecute.FORBIDDEN
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
private fun isResourceAccess(login: String, role: String, resourse: String): Boolean {
    val userId = (dataBase.getUserByLogin(login) as User).id

    return dataBase.getResourses().any {it.userId == userId && it.role.name == role && Utils.isResource(resourse, it.resource)}
}
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
fun ByteArray.toStr(): String = MessageDigest.getInstance("MD5").digest(sourse.toByteArray()).joinToString("") { "%02x".format(it) }
``` 


## Объект: **Аргумент**

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

1. User(0, "admin", "2b0f98d3c29b6eff9634917feef9fe6b", "salt") // pass: 00000

```

## Список: **Ресурсов**
```kotlin

1. RoleResource(Roles.READ, "A", 0)
2. RoleResource(Roles.WRITE, "A.B", 0)

```


# Тестовые сценарии
## Логин, Пароль
```kotlin
// Code: 0 | Ввести правильные данные
java -jar "main.jar" -login admin -password 00000

// Code: 0 | Ввести правильные данные, но поменять порядок записи
java -jar "main.jar" -password 00000 -login admin
        
// Code: 2 | Ввести неверный формат логина
java -jar "main.jar" -login "?login?" -password 00000

// Code: 3 | Ввести данные не зарегистрированного пользователя
java -jar "main.jar" -login NotRegUser -password NotRegUser
        
// Code: 4 | Ввести верный логин, но неверный пароль
java -jar "main.jar" -login admin -password WrongPassword
```

## Логин, Пароль, Роль, Ресурс
```kotlin
// Code: 0 | Ввести вверные данные для доступа к ресурсу
java -jar "main.jar" -login admin -password 00000 -role READ -resource A

// Code: 0 | Ввести вверные данные для доступа к ресурсу
java -jar "main.jar" -login admin -password 00000 -role WRITE -resource "A.B"

// Code: 5 | Ввести не существующую роль
java -jar "main.jar" -login admin -password 00000 -role UNKNOWNROLE -resource A

// Code: 5 | Ввести не существующий ресурс
java -jar "main.jar" -login admin -password 00000 -role READ -resource NOT_FOUND_RESOURCE

// Code: 6 | Ввести данные ресурса с ролью, к которому нет доступа у пользователя
java -jar "main.jar" -login admin -password 00000 -role EXECUDE -resource A
```

## Логин, Пароль, Роль, Ресурс, Дата начала, Дата завершения, Объем
```kotlin
// Code: 0 | Ввести валидные данные
java -jar "main.jar" -login admin -password 00000 -role READ -resource A -ds "2015-12-31" -de "2016-12-31" -vol 55

// Code: 7 | Дата начала первышает дату завершения
java -jar "main.jar" -login admin -password 00000 -role READ -resource A -ds "2017-12-31" -de "2016-12-31" -vol 55

// Code: 7 | Ввести не верный формат даты
java -jar "main.jar" -login admin -password 00000 -role READ -resource A -ds "time_start" -de "time_end" -vol 55

// Code: 7 | Ввести не верный объем (не является числом)
java -jar "main.jar" login admin -password 00000 -role READ -resource A -ds "2017-12-31" -de "2016-12-31" -vol NOT_VALID -vol 55
```

## Справка

```kotlin
// Code: 0 | Передать параметром -h
java -jar "main.jar" login admin -h

// Code: 0 | Не передавать параметров
java -jar "main.jar" ""
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
        }
    }
    
    System.exit(exitCode)
}

```



