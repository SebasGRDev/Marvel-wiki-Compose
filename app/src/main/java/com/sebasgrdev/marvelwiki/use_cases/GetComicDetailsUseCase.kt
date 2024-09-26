package com.sebasgrdev.marvelwiki.use_cases

import com.sebasgrdev.marvelwiki.model.domain.CharactersRepository
import com.sebasgrdev.marvelwiki.model.domain.comic.Comic
import com.sebasgrdev.marvelwiki.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetComicDetailsUseCase @Inject constructor(
    private val repository: CharactersRepository
) {
    operator fun invoke(characterId: Int): Flow<Response<List<Comic>>> = flow {
        try {
            emit(Response.Loanding())
            val list = repository.getComicDetails(characterId).data.results.map {
                it.toComic()
            }
            emit(Response.Success<List<Comic>>(list))
        } catch (e: HttpException) {
            emit(Response.Error<List<Comic>>(e.printStackTrace().toString()))
        } catch (e: IOException) {
            emit(Response.Error<List<Comic>>(e.printStackTrace().toString()))
        }
    }
}