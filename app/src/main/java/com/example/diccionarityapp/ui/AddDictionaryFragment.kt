package com.example.diccionarityapp.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.diccionarityapp.R
import com.example.diccionarityapp.databinding.FragmentAddDictionaryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddDictionaryFragment : Fragment() {

    private val viewModel: DictionaryViewModel by activityViewModels()
    private var binding: FragmentAddDictionaryBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddDictionaryBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            viewModel = this@AddDictionaryFragment.viewModel
            addFragment = this@AddDictionaryFragment
            lifecycleOwner = viewLifecycleOwner

            //THIS IS OPTIONAL
//            listOf(wordTv, meaningTv).forEach { tv ->
//                tv.addTextChangedListener(onTextChanged = {s,_,_,_ ->
//                    actionBtn.isEnabled = wordTv.text.toString().isNotEmpty() && meaningTv.text.toString().isNotEmpty()
//                })
//            }
        }
    }
    /*
    Set appropriate text for the button: It only can be: Insert or Update
     */
    fun displayButtonText() = getString(if(viewModel.insertActionStatus) R.string.lbl_button_insert else R.string.lbl_button_update)

    /*
    Insert new item in the database
     */
    fun insert() = binding?.let {
        val newWord = viewModel.makeNewEntity(
            name = it.wordTv.text.toString(),
            meaning = it.meaningTv.text.toString()
        )
        viewModel.insertWord(newWord)
        findNavController().navigateUp()
    }

    fun update() = binding?.let {
        val updatedWord = viewModel.makeNewEntity(
            id = viewModel.selectedWord.value?.id!!,
            name = it.wordTv.text.toString(),
            meaning = it.meaningTv.text.toString()
        )
        viewModel.updateWord(updatedWord)
        findNavController().navigateUp()
    }

    fun onClick() {
        if (viewModel.insertActionStatus) {
            insert()
        } else {
            update()
        }
    }
}