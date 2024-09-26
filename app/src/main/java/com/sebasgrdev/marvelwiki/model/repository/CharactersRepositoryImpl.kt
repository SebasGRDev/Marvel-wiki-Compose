package com.sebasgrdev.marvelwiki.model.repository

import com.sebasgrdev.marvelwiki.model.api.HeroServiceAPI
import com.sebasgrdev.marvelwiki.model.data.comicdata.ComicData
import com.sebasgrdev.marvelwiki.model.data.herodata.HeroesData
import com.sebasgrdev.marvelwiki.model.domain.CharactersRepository
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val api: HeroServiceAPI
) : CharactersRepository {
    override suspend fun getCharacters(offset: Int): HeroesData {
        return api.getCharacters(offset = offset.toString())
    }

    override suspend fun getComicDetails(characterId: Int): ComicData {
        return api.getComicDetails(characterId.toString())
    }

}