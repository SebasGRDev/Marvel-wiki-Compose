package com.sebasgrdev.marvelwiki.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebasgrdev.marvelwiki.ui.CharacterList.CharacterListState
import com.sebasgrdev.marvelwiki.ui.Comiclist.ComicListState
import com.sebasgrdev.marvelwiki.use_cases.CharactersUseCase
import com.sebasgrdev.marvelwiki.use_cases.GetComicDetailsUseCase
import com.sebasgrdev.marvelwiki.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val charactersUseCase : CharactersUseCase,
    private val comicUseCase: GetComicDetailsUseCase
) : ViewModel() {

    private val characterValue = MutableStateFlow(CharacterListState())
    var _characterValue : StateFlow<CharacterListState> = characterValue

    private val comicValue = MutableStateFlow(ComicListState())
    var _comicValue : StateFlow<ComicListState> = comicValue

    fun getAllCharacters(offset: Int) = viewModelScope.launch(Dispatchers.IO) {
        charactersUseCase(offset).collect {
            when(it) {
                is Response.Success -> {
                    characterValue.value = CharacterListState(characterList = it.data ?: emptyList())
                }
                is Response.Loanding -> {
                    characterValue.value = CharacterListState(isLoanding = true)
                }
                is Response.Error -> {
                    characterValue.value = CharacterListState(error = it.message?: "An Unexpected Error")
                }
            }
        }
    }

    fun getDetailComic(characterId: Int) = viewModelScope.launch(Dispatchers.IO) {
        comicUseCase(characterId).collect {
            when(it) {
                is Response.Success -> {
                    comicValue.value = ComicListState(comicList = it.data ?: emptyList())
                }
                is Response.Loanding -> {
                    comicValue.value = ComicListState(isLoanding = true)
                }
                is Response.Error -> {
                    comicValue.value = ComicListState(error = it.message?: "An Unexpected Error")
                }
            }
        }
    }

}