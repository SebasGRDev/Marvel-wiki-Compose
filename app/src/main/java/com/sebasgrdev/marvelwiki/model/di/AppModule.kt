package com.sebasgrdev.marvelwiki.model.di

import com.sebasgrdev.marvelwiki.model.api.HeroServiceAPI
import com.sebasgrdev.marvelwiki.model.domain.CharactersRepository
import com.sebasgrdev.marvelwiki.model.repository.CharactersRepositoryImpl
import com.sebasgrdev.marvelwiki.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideApi(): HeroServiceAPI{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HeroServiceAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideCharactersRepository(api: HeroServiceAPI) : CharactersRepository {
        return CharactersRepositoryImpl(api)
    }
}