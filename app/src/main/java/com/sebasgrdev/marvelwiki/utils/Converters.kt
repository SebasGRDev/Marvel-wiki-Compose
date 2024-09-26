package com.sebasgrdev.marvelwiki.utils

import androidx.compose.ui.input.key.type
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sebasgrdev.marvelwiki.model.data.comicdata.Date
import com.sebasgrdev.marvelwiki.model.data.herodata.Url
import com.sebasgrdev.marvelwiki.model.data.comicdata.Thumbnail

class Converters {
    @TypeConverter
    fun fromStringList(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toStringList(list: List<String>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromUrlList(value: String): List<Url> {
        val listType = object : TypeToken<List<Url>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toUrlList(list: List<Url>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromThumbnail(value: String): Thumbnail? {
        return Gson().fromJson(value, Thumbnail::class.java)
    }

    @TypeConverter
    fun toThumbnail(thumbnail: Thumbnail?): String {
        return Gson().toJson(thumbnail)
    }

    @TypeConverter
    fun fromDateList(value: String): List<Date> {
        val listType = object : TypeToken<List<Date>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toDateList(list: List<Date>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}