package com.sebasgrdev.marvelwiki.model.api

import androidx.compose.ui.geometry.Offset
import com.sebasgrdev.marvelwiki.model.data.herodata.Data
import com.sebasgrdev.marvelwiki.model.data.herodata.HeroesData
import com.sebasgrdev.marvelwiki.model.data.herodata.Result
import com.sebasgrdev.marvelwiki.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HeroServiceAPI {
    @GET("/v1/public/characters")
    suspend fun getCharacters(
        @Query("apikey") apiKey: String = Constants.API_KEY,
        @Query("ts") ts: String = Constants.timeStamp,
        @Query("hash") hash: String = Constants.hash(),
        @Query("offset") offset: String
    ): HeroesData
}