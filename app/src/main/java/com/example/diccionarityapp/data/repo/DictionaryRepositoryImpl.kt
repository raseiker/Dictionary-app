package com.example.diccionarityapp.data.repo

import com.example.diccionarityapp.data.database.DictionaryDao
import com.example.diccionarityapp.data.database.WordEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Class that implements [DictionaryRepository]. It will be used for the view model
 */
class DictionaryRepositoryImpl @Inject constructor(
    private val dao: DictionaryDao
): DictionaryRepository {

    //insert one item into the word table
    override suspend fun insertWord(wordEntity: WordEntity) {
        dao.insert(wordEntity)
    }

    //get all rows in the word table
    override fun getAllWords(): Flow<List<WordEntity>> {
        return dao.getAllWord()
    }

    override suspend fun deleteWord(wordEntity: WordEntity) {
        dao.delete(wordEntity)
    }

    override suspend fun updateWord(wordEntity: WordEntity) {
        dao.update(wordEntity)
    }
}