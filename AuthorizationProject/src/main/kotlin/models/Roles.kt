package main.kotlin.models

enum class Roles() {
    READ,
    WRITE,
    EXECUTE;

    companion object {
        fun roleValidate(role: String): Boolean {
            return Roles.values().any { it.name == role }
        }
    }
}