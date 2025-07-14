package com.example.infinito.utils.md5

import java.security.MessageDigest

fun String.toMD5(): String {
    return try {
        val bytes = MessageDigest.getInstance("MD5").digest(this.toByteArray())
        val result = StringBuilder()
        for (byte in bytes) {
            result.append(String.format("%02x", byte))
        }
        result.toString()
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }
}
