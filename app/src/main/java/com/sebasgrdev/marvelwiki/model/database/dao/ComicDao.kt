package com.sebasgrdev.marvelwiki.model.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sebasgrdev.marvelwiki.model.domain.comic.Comic

@Dao
interface ComicDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComics(comics: Comic)

    @Query("SELECT * FROM Comic WHERE id = :comicId")
    suspend fun getComicById(comicId: Int): Comic?

    @Query("SELECT * FROM Comic")
    suspend fun getAllComics(): List<Comic>
}