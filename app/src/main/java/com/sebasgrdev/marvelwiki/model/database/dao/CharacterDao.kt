package com.sebasgrdev.marvelwiki.model.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sebasgrdev.marvelwiki.model.domain.character.Character

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacters(characters: Character)

    @Query("SELECT * FROM Character")
    suspend fun getAllCharacters(): List<Character>
}
