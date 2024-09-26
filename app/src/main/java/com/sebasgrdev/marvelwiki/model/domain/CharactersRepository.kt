package com.sebasgrdev.marvelwiki.model.domain

import com.sebasgrdev.marvelwiki.model.data.comicdata.ComicData
import com.sebasgrdev.marvelwiki.model.data.herodata.HeroesData

interface CharactersRepository {
    suspend fun getCharacters(offset: Int): HeroesData

    suspend fun getComicDetails(characterId: Int): ComicData
}