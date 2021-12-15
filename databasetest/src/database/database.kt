package databasetest

fun main(args: Array<String>){
    connect()
}

fun connect() {
System.setenv
    val url = System.getenv("url")
    val login = System.getenv("login")
    val password = System.getenv("password")

    println("TEXT: ${url}, ${login}, ${password}")
}

