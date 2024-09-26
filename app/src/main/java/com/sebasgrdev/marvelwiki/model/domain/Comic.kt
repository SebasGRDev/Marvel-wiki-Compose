package com.sebasgrdev.marvelwiki.model.domain

import com.sebasgrdev.marvelwiki.model.data.comicdata.Date
import com.sebasgrdev.marvelwiki.model.data.comicdata.Thumbnail

data class Comic(
    val id: Int,
    val title: String,
    val resourceURI: String,
    val thumbnail: Thumbnail,
    val date: List<Date>
)