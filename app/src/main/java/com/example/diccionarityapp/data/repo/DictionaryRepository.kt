package com.example.diccionarityapp.data.repo

import com.example.diccionarityapp.data.database.WordEntity
import kotlinx.coroutines.flow.Flow

/**
 * This interface serves as a entry point for the view model
 */
interface DictionaryRepository {

    //fun to insert one word into Dictionary database
    suspend fun insertWord(wordEntity: WordEntity)

    //fun to get all elements in the Word database table
    fun getAllWords(): Flow<List<WordEntity>>

    //fun to update one word into Dictionary database
    suspend fun updateWord(wordEntity: WordEntity)

    //fun to delete one word into Dictionary database
    suspend fun deleteWord(wordEntity: WordEntity)

}