package com.sebasgrdev.marvelwiki.use_cases

import coil.network.HttpException
import com.sebasgrdev.marvelwiki.model.domain.Character
import com.sebasgrdev.marvelwiki.model.domain.CharactersRepository
import com.sebasgrdev.marvelwiki.utils.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class CharactersUseCase @Inject constructor(
    private val repository: CharactersRepository
) {
    operator fun invoke(offset: Int) : Flow<Response<List<Character>>> = flow {
        try {
            emit(Response.Loanding<List<Character>>())
            val list = repository.getCharacters(offset).data.results.map {
                it.toCharacter()
            }
            emit(Response.Success<List<Character>>(list))
        } catch (e: HttpException) {
            emit(Response.Error<List<Character>>(e.printStackTrace().toString()))
        }
        catch (e:IOException) {
            emit(Response.Error<List<Character>>(e.printStackTrace().toString()))
        }
    }
}