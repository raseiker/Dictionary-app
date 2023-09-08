package com.example.diccionarityapp.ui.listener

import com.example.diccionarityapp.data.database.WordEntity

class DictionaryItemListener(
    val action: (wordEntity: WordEntity) -> Unit,
    val deleteListener: (wordEntity: WordEntity) -> Unit
) {
    fun onClick(wordEntity: WordEntity) = action(wordEntity)
    fun onDelete(wordEntity: WordEntity) = deleteListener(wordEntity)
}