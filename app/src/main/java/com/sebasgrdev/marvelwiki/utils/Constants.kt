package com.sebasgrdev.marvelwiki.utils

import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

class Constants {

    companion object {
        const val BASE_URL = "https://gateway.marvel.com"
        val timeStamp = Timestamp(System.currentTimeMillis()).time.toString()
        const val API_KEY = "ca085660c2218dc8fe027cbaf4ee96cd"
        const val PRIVATE_KEY = "616c526b2cf7d96ab262d407c8bd6795622878c6"
        fun hash() :String {
            val input = "$timeStamp$PRIVATE_KEY$API_KEY"
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
        }
        const val HASH = "0add3a744ca368b26b221f2d8aa2df19"
    }

}