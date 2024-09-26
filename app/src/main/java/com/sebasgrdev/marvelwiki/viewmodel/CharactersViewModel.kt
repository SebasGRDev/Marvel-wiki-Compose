package com.sebasgrdev.marvelwiki.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebasgrdev.marvelwiki.model.database.db.AppDatabase
import com.sebasgrdev.marvelwiki.model.domain.character.Character
import com.sebasgrdev.marvelwiki.model.domain.comic.Comic
import com.sebasgrdev.marvelwiki.ui.CharacterList.CharacterListState
import com.sebasgrdev.marvelwiki.ui.Comiclist.ComicListState
import com.sebasgrdev.marvelwiki.use_cases.CharactersUseCase
import com.sebasgrdev.marvelwiki.use_cases.GetComicDetailsUseCase
import com.sebasgrdev.marvelwiki.utils.Response
import com.sebasgrdev.marvelwiki.utils.isNetworkAvailable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val charactersUseCase: CharactersUseCase,
    private val comicUseCase: GetComicDetailsUseCase,
    private val application: Application
) : ViewModel() {

    private val characterValue = MutableStateFlow(CharacterListState())
    var _characterValue: StateFlow<CharacterListState> = characterValue

    private val comicValue = MutableStateFlow(ComicListState())
    var _comicValue: StateFlow<ComicListState> = comicValue

    private var originalCharacters: List<Character> = emptyList()
    private var originalComic: List<Comic> = emptyList()

    private val characterDao = AppDatabase.getDatabase(application).characterDao()
    private val comicDao = AppDatabase.getDatabase(application).comicDao()

    fun getAllCharacters(offset: Int) = viewModelScope.launch(Dispatchers.IO) {
        if (application.isNetworkAvailable()) {
            charactersUseCase(offset).collect {
                when (it) {
                    is Response.Success -> {
                        characterValue.value =
                            CharacterListState(characterList = it.data ?: emptyList())
                        originalCharacters = it.data ?: emptyList()

                        originalCharacters.forEach { character ->
                            characterDao.insertCharacters(
                                Character(
                                    id = character.id,
                                    name = character.name,
                                    thumbnail = "${character.thumbnail}.${character.thumbnailExt}",
                                    thumbnailExt = character.thumbnailExt,
                                    comics = emptyList(),
                                    urls = emptyList()
                                )
                            )
                        }
                    }

                    is Response.Loanding -> {
                        characterValue.value = CharacterListState(isLoanding = true)
                    }

                    is Response.Error -> {
                        characterValue.value =
                            CharacterListState(error = it.message ?: "An Unexpected Error")
                    }
                }
            }
        } else {
            val dbCharacters = characterDao.getAllCharacters()
            if (dbCharacters.isNotEmpty()) {
                val characterList = dbCharacters.map { character ->
                    Character(
                        id = character.id,
                        name = character.name,
                        thumbnail = "${character.thumbnail}.${character.thumbnailExt}",
                        thumbnailExt = character.thumbnailExt,
                        comics = emptyList(),
                        urls = emptyList()

                    )
                }
                characterValue.value = CharacterListState(characterList = characterList)
            } else {
                characterValue.value =
                    CharacterListState(error = "No data available. Please connect to the internet.")
            }
        }
    }

    fun getDetailComic(characterId: Int) = viewModelScope.launch(Dispatchers.IO) {
        if (application.isNetworkAvailable()) {
            comicUseCase(characterId).collect {
                when (it) {
                    is Response.Success -> {
                        comicValue.value = ComicListState(comicList = it.data ?: emptyList())
                        originalComic = it.data ?: emptyList()
                        originalComic.forEach { comic ->
                            comicDao.insertComics(
                                Comic(
                                    id = comic.id,
                                    title = comic.title,
                                    thumbnail = "${comic.thumbnail}.${comic.thumbnailExt}",
                                    thumbnailExt = comic.thumbnailExt,
                                    date = comic.date,
                                    resourceURI = comic.resourceURI
                                )
                            )

                        }
                    }
                    is Response.Loanding -> {
                        comicValue.value = ComicListState(isLoanding = true)
                    }

                    is Response.Error -> {
                        comicValue.value =
                            ComicListState(error = it.message ?: "An Unexpected Error")
                    }
                }
            }
        } else {
            val dbComics = comicDao.getAllComics()
            if (dbComics.isNotEmpty()) {
                val comicList = dbComics.map { comic ->
                    Comic(
                        id = comic.id,
                        title = comic.title,
                        thumbnail = "${comic.thumbnail}.${comic.thumbnailExt}",
                        date = comic.date,
                        resourceURI = comic.resourceURI,
                        thumbnailExt = comic.thumbnailExt
                    )
                }
                comicValue.value = ComicListState(comicList = comicList)
            } else {
                comicValue.value = ComicListState(error = "No comics available.")
            }
        }
    }

}