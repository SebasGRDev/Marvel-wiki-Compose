package com.sebasgrdev.marvelwiki.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebasgrdev.marvelwiki.ui.CharacterList.CharacterListState
import com.sebasgrdev.marvelwiki.use_cases.CharactersUseCase
import com.sebasgrdev.marvelwiki.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val charactersUseCase : CharactersUseCase
) : ViewModel() {

    private val characterValue = MutableStateFlow(CharacterListState())
    var _characterValue : StateFlow<CharacterListState> = characterValue

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

}