package com.sebasgrdev.marvelwiki.ui.CharacterList

import com.sebasgrdev.marvelwiki.model.domain.Character
import com.sebasgrdev.marvelwiki.utils.Response

data class CharacterListState(
    val isLoanding: Boolean = false,
    val characterList : List<Character> = emptyList(),
    val error: String = ""
)
