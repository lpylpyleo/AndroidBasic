package com.example.androidbasic.util

import android.content.Context
import android.util.TypedValue
import java.net.URLEncoder
import java.security.MessageDigest
import java.util.*

class Util {
    companion object {
        fun dpToPx(dp: Float, context: Context): Int {
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.resources.displayMetrics).toInt()
        }

        fun signParam(param: LinkedHashMap<String, Any>): SortedMap<String, Any> {
            val appkey = "1d8b6e7d45233436"
            val appsec = "560c52ccd288fed045859ed18bffd973"
            val enc = "UTF-8"
            val cypher = MessageDigest.getInstance("MD5")
            param["appkey"] = appkey
            val sortedMap = param.toSortedMap { o1, o2 -> o1.compareTo(o2) }
            val query = sortedMap.entries.map { e ->
                "${URLEncoder.encode(e.key, enc)}=${URLEncoder.encode(e.value.toString(), enc)}"
            }.joinToString("&")
            val sign = cypher.digest((query + appsec).encodeToByteArray())
            sortedMap["sign"] = convertByteToHexadecimal(sign)
            return sortedMap
        }

        private fun convertByteToHexadecimal(byteArray: ByteArray): String {
            val hex = StringBuffer()
            // Iterating through each byte in the array
            for (i in byteArray) {
                hex.append(String.format("%02x", i))
            }
            return hex.toString()
        }
    }
}