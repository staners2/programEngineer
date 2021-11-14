import java.security.MessageDigest
import kotlin.random.Random
import kotlinx.cli.*

object Utils {
    fun genearateSalt(): String = Random.nextBytes(32).toStr()

    fun encode(password: String, salt: String): String = getHash(getHash(password) + salt)

    fun getHash(sourse: String): String = MessageDigest.getInstance("MD5").digest(sourse.toByteArray()).toStr()

    private fun ByteArray.toStr(): String = joinToString("") { "%02x".format(it) }
}