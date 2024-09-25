package com.sebasgrdev.marvelwiki.model.domain

data class Character(
    val id: Int,
    val name: String,
    val thumbnail: String,
    val thumbnailExt: String,
    val comics: List<String>
)
