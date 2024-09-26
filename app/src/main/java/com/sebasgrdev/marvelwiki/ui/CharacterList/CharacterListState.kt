package com.sebasgrdev.marvelwiki.ui.CharacterList

import com.sebasgrdev.marvelwiki.model.domain.character.Character

data class CharacterListState(
    val isLoanding: Boolean = false,
    val characterList : List<Character> = emptyList(),
    val error: String = ""
)
