package com.sebasgrdev.marvelwiki.model.data.herodata

data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<Item>,
    val returned: Int
)