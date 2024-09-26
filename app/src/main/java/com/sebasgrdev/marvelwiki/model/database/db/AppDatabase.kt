package com.sebasgrdev.marvelwiki.model.database.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sebasgrdev.marvelwiki.model.database.dao.CharacterDao
import com.sebasgrdev.marvelwiki.model.database.dao.ComicDao
import com.sebasgrdev.marvelwiki.model.domain.character.Character
import com.sebasgrdev.marvelwiki.model.domain.comic.Comic
import com.sebasgrdev.marvelwiki.utils.Converters

@Database(
    entities = [Character::class, Comic::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun comicDao(): ComicDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "heroes_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }

    }
}