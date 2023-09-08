package com.example.diccionarityapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.diccionarityapp.data.database.WordEntity
import com.example.diccionarityapp.databinding.ItemListBinding
import com.example.diccionarityapp.ui.listener.DictionaryItemListener

/**
 * Adapter class that sets up the RecyclerView data and defines a DiffCallBack
 */
class DictionaryAdapter(
    private val listener: DictionaryItemListener
): ListAdapter<WordEntity, DictionaryAdapter.DictionaryViewHolder>(DiffCallback) {

    inner class DictionaryViewHolder(
        private val binding: ItemListBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(wordEntity: WordEntity){
            binding.wordEntity = wordEntity
            binding.itemListener = listener
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DictionaryViewHolder {
        return DictionaryViewHolder(ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: DictionaryViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    companion object DiffCallback: DiffUtil.ItemCallback<WordEntity>() {
        override fun areItemsTheSame(oldItem: WordEntity, newItem: WordEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: WordEntity, newItem: WordEntity): Boolean {
            return oldItem.meaning == newItem.meaning && oldItem.name == newItem.name
        }
    }
}