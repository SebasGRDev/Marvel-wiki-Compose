package com.sebasgrdev.marvelwiki.model.data.comicdata

data class Characters(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)