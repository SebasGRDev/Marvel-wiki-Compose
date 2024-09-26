package com.sebasgrdev.marvelwiki.model.data.comicdata

data class Creators(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemX>,
    val returned: Int
)