package com.sebasgrdev.marvelwiki.model.data.comicdata

data class ComicData(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    val `data`: Data,
    val etag: String,
    val status: String
)