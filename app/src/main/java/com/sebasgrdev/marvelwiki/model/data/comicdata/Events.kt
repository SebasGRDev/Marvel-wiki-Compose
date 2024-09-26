package com.sebasgrdev.marvelwiki.model.data.comicdata

data class Events(
    val available: Int,
    val collectionURI: String,
    val items: List<Any>,
    val returned: Int
)