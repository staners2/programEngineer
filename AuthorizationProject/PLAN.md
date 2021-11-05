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
class RoleResourses {
    TODO ...
}
```

* Создать объект **Аргумент**
```kotlin
data class Arguments(
    val h: Bool,
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
    TRUE(0),
    HELP(1),
    NOT_FORMAT_LOGIN(2),
    NOT_LOGIN(3),
    NOT_PASSWORD(4),
    UNKNOWN_ROLES(5),
    FORBIDDEN(6),
    INCORRECT_ACTIVITY(7)
}
```

* Создать объект **БазыДанных**
```kotlin
class DataBase {
    private val roleResourses: List<RoleResourses> = listOf(...)
    private val users: List<User> = listOf(...)
}
```

* Создать объект **АутентификацииПровайдер**
```kotlin
// Для аутентификации (логин + pass)
class AuthenticationProvider {
    TODO ...
}
```

* Создать объект **АвторизацияПровайдер**
```kotlin
// Для авторизации (user + resourse)
class AuthorizeProvider {
    TODO ...
}
```

* Создать объект **Утилит**
```kotlin
// Для хеширования паролей
import java.security.MessageDigest
import kotlin.random.Random

class Utils {
    companion object {

    }
}
```


# Методы объектов
## Объект: **База Данных** 

* Метод: Возвращающий объект пользователя по логину
```kotlin
fun getUserByLogin(login: String): User? {
    return users.find { it.login == login }
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
* Метод: TODO ...
```kotlin

```

## Объект: **АвторизацияПровайдер** 
* Метод: TODO ...
```kotlin

```

## Объект: **Утилит** 
* Метод: Генерирует соль случайно
```kotlin
fun genearateSalt(): String = Random.nextBytes(64).joinToString("") { "%02x".format(it) }
```

* Метод: Шифрует пароль
```kotlin
fun encode(password: String, salt: String): String = hashCode(hashCode(password) + salt)
```

* Метод: Парсит строку и возвращает объект Аргументов
```kotlin
fun parseArgument(password: String, salt: String): String = hashCode(hashCode(password) + salt)
```


# Тестовые данные

```kotlin

TODO ...

```

# Тестовые сценарии
```kotlin

TODO ...

```



