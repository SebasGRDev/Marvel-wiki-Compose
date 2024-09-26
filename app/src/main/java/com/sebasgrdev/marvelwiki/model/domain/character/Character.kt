package com.sebasgrdev.marvelwiki.model.domain.character

import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sebasgrdev.marvelwiki.model.data.herodata.Url

@Entity
data class Character(
    @PrimaryKey
    val id: Int,
    val name: String,
    val thumbnail: String,
    val thumbnailExt: String,
    val comics: List<String>,
    val urls: List<Url>
)
