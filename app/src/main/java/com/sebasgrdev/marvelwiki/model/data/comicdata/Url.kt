package com.sebasgrdev.marvelwiki.model.data.comicdata

import java.io.Serializable

data class Url(
    val type: String,
    val url: String
) : Serializable