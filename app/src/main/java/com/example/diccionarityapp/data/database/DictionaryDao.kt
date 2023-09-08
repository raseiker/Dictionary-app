package com.example.diccionarityapp.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 * Interface that contains functions to manipulate the database
 */
@Dao
interface DictionaryDao {
    @Query("SELECT * from word ORDER BY name ASC")
    fun getAllWord(): Flow<List<WordEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(wordEntity: WordEntity)

    @Update
    suspend fun update(wordEntity: WordEntity)

    @Delete
    suspend fun delete(wordEntity: WordEntity)
}