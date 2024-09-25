package com.sebasgrdev.marvelwiki.model.domain

import com.sebasgrdev.marvelwiki.model.api.HeroServiceAPI
import com.sebasgrdev.marvelwiki.model.data.herodata.HeroesData
import javax.inject.Inject

interface CharactersRepository {
    suspend fun getCharacters(offset: Int): HeroesData
}