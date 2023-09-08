package com.example.diccionarityapp.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.diccionarityapp.data.database.WordEntity
import com.example.diccionarityapp.data.repo.DictionaryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DictionaryViewModel @Inject constructor(
    private val repo: DictionaryRepository
): ViewModel() {

    //this works. Only can be read by the observer method
    val word: LiveData<List<WordEntity>> = repo.getAllWords().asLiveData()

    private var _selectedWord: MutableLiveData<WordEntity>? = MutableLiveData()
    val selectedWord: LiveData<WordEntity> = _selectedWord!!

    private var _insertActionStatus = true
    val insertActionStatus get() = _insertActionStatus

    init {
//        getAllTwo()
//        getSize()
    }

    fun getAllTwo() = viewModelScope.launch {
        repo.getAllWords().collect { list ->
            Log.d("tableItems", "all items: ${list.size}")
            Log.d("tableItems", "all items: ${list[0].name}")
        }
    }

    /*
    Insert new WordEntity in the database by calling the repo
     */
    fun insertWord(wordEntity: WordEntity) = viewModelScope.launch(Dispatchers.IO) {
        try {
            repo.insertWord(wordEntity)
        } catch (e: Exception) {
            Log.d("insertItem", "error: ${e.message}")
        }
    }

    fun updateWord(wordEntity: WordEntity) = viewModelScope.launch(Dispatchers.IO) {
        try {
            repo.updateWord(wordEntity)
        } catch(e: Exception) {
            Log.d("updateItem", "error: ${e.message}")
        }
    }

    fun deleteWord(wordEntity: WordEntity) = viewModelScope.launch(Dispatchers.IO) {
        try {
            repo.deleteWord(wordEntity)
        } catch (e: Exception) {
            Log.d("deleteItem", "error: ${e.message}")
        }
    }

    fun setUpInsertActionStatus(status: Boolean) {
        _insertActionStatus = status
        resetSelectedWord(status)
    }

    private fun resetSelectedWord(isInsert: Boolean) {
        if (isInsert) _selectedWord?.value = WordEntity(name = "", meaning = "")
    }

    /*
    Get the current item selected by user and save it in the [_selectedWord] variable
     */
    fun onItemClicked(wordEntity: WordEntity) {
        setUpInsertActionStatus(false)
        _selectedWord?.value = wordEntity
        Log.d("selectedItem", "item: ${_selectedWord?.value?.name}, ${_selectedWord?.value?.meaning}")
    }

    /*
    Create new WordEntity from parameters and return it
     */
    fun makeNewEntity(name: String, meaning: String, id: Int = 0) = WordEntity(
        id = id,
        name = name,
        meaning = meaning
    )

}