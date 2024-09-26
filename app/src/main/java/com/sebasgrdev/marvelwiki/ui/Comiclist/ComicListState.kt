package com.sebasgrdev.marvelwiki.ui.Comiclist

import com.sebasgrdev.marvelwiki.model.domain.comic.Comic

data class ComicListState(
    val isLoanding: Boolean = false,
    val comicList : List<Comic> = emptyList(),
    val error: String = ""
)
