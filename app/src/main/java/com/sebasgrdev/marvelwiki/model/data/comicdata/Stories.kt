package com.sebasgrdev.marvelwiki.model.data.comicdata

data class Stories(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemXX>,
    val returned: Int
)