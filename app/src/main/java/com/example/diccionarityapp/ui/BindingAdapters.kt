package com.example.diccionarityapp.ui

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.diccionarityapp.data.database.WordEntity
import com.example.diccionarityapp.ui.adapter.DictionaryAdapter

@BindingAdapter("data")
fun RecyclerView.bindData(data: List<WordEntity>?) {
    //parse adapter to use our list adapter
    data?.let{
        val listAdapter = adapter as DictionaryAdapter
        listAdapter.submitList(it)
    }
}