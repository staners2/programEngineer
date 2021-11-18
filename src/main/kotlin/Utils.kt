package main.kotlin

import java.security.MessageDigest

object Utils {

    fun encode(password: String, salt: String): String = getHash(getHash(password) + salt)

    private fun getHash(sourse: String): String = MessageDigest.getInstance("MD5").digest(sourse.toByteArray()).toStr()

    private fun ByteArray.toStr(): String = joinToString("") { "%02x".format(it) }
}