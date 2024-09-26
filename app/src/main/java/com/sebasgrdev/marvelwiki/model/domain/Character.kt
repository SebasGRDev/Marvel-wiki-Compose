package com.sebasgrdev.marvelwiki.model.domain

import com.sebasgrdev.marvelwiki.model.data.herodata.Url

data class Character(
    val id: Int,
    val name: String,
    val thumbnail: String,
    val thumbnailExt: String,
    val comics: List<String>,
    val urls: List<Url>
)
