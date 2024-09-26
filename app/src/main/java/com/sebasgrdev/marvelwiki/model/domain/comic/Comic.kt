package com.sebasgrdev.marvelwiki.model.domain.comic

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sebasgrdev.marvelwiki.model.data.comicdata.Date
import com.sebasgrdev.marvelwiki.model.data.comicdata.Thumbnail

@Entity
data class Comic(
    @PrimaryKey
    val id: Int,
    val title: String,
    val resourceURI: String,
    val thumbnail: String?,
    val thumbnailExt: String?,
    val date: List<Date>
)