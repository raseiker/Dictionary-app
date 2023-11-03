package com.example.diccionarityapp.ui

import android.widget.Button
import androidx.core.widget.addTextChangedListener
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.diccionarityapp.data.database.WordEntity
import com.example.diccionarityapp.ui.adapter.DictionaryAdapter
import com.google.android.material.textfield.TextInputEditText

@BindingAdapter("data")
fun RecyclerView.bindData(data: List<WordEntity>?) {
    //parse adapter to use our list adapter
    data?.let{
        val listAdapter = adapter as DictionaryAdapter
        listAdapter.submitList(it)
    }
}

/*
This method receive two parameters. Link each input-edit-text with text-change-listener
The button that also is passed as parameter, will be enabled accordingly
 */
@BindingAdapter("otherInput", "button")
fun TextInputEditText.bindWatcher(otherInputEditText: TextInputEditText,btn: Button) {
    listOf(this, otherInputEditText).forEach { tv ->
        tv.addTextChangedListener(onTextChanged = {_,_,_,_ ->
            btn.isEnabled = this.text.toString().trim().isNotEmpty()
                    && otherInputEditText.text.toString().trim().isNotEmpty()
        })
    }
}