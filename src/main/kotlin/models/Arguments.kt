package main.kotlin.models

import kotlinx.cli.*

data class Arguments(
    val login: String?,
    val password: String?,
    val role: String?,
    val resource: String?,
    val ds: String?,
    val de: String?,
    val vol: String?
)