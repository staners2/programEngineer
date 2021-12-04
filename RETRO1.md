# Ретроспектива

Планировалось - **было:**
Реализовано   - **стало:** 

1. Проверку на доступ к ресурсу вынести из Объекта RoleResourse в Utils, т.к. это модель данных и должны быть без логики
```kotlin
fun isResource(resource: String, itemResource: String): Boolean
```
2. Метод для парсинга аргументов вынести из объекта Arguments в Utils, т.к. это модель данных и должны быть без логики
```kotlin
fun parseArguments(args: Array<String>): Arguments
```
3. Информацию связанную логикой с объектом не стоит размещать в разных местах, например как с Ожидаемым результатами в файле прогонки тестов
Ошибкой было размещать ожидаемые результатом массивом цифр, было заменено на добавление ожидаемого кода первым символом в строке передаваемых аргументов
```kotlin
// Было
expectedCodes=(0 0 2 3 4 0 0 5 6 0 7 7 7 0 0)
// Стало
input[9]="0-login admin -password 00000 -role READ -resource A -ds 2015-12-31 -de 2016-12-31 -vol 55"
```
4. Все зависимости необходимо внедрять (dependency injection)
```kotlin
// Было:
object AuthenticationProvider {
    private val dataBase: DataBaseProvider = DataBaseProvider()
}
// Стало:
fun main(args: Array<String>){
    val dataBaseProvider = DataBaseProvider()
    val authenticationProvider = AuthenticationProvider(dataBaseProvider)
}
```
5. Избегать большой вложенности if
```kotlin
// Было:
if (arguments.login != null && arguments.password != null){
    exitCode = AuthenticationProvider.authenticate(arguments.login, arguments.password)

    if (arguments.role != null && arguments.resource != null){
        // Провести авторизацию
        ...
    }
}  
// Стало:
if (arguments.login != null && arguments.password != null){
    exitCode = AuthenticationProvider.authenticate(arguments.login, arguments.password)
}  
if (arguments.role != null && arguments.resource != null){
    // Провести авторизацию
    ...
}
```
6. Не забывать про Функции высшего порядка
```kotlin
// Было:
for (item in dataBase.getResourses()) {
    if (item.userId == userId && item.role.name == role && Utils.isResource(resourse, item.resource)) {
        return true
    }
}
return false
// Стало:
return dataBase.getResourses().any {it.userId == userId && it.role.name == role && Utils.isResource(resourse, it.resource)}
```