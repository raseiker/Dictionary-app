package com.example.diccionarityapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
//            actionBtn.isEnabled = isValidEntries()
            lifecycleOwner = viewLifecycleOwner
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

//    fun isValidEntries(): LiveData<Boolean>  {
//        return liveData { binding?.meaningTv.toString().isNotEmpty() && binding?.wordTv.toString().isNotEmpty() }
//    }
    fun isValidEntries(): Boolean  {
        viewModel.isValidEntries(binding?.meaningTv?.text.toString(), binding?.wordTv?.text.toString())
        return viewModel.isButtonEnabled.value ?: false
    }
        //true * false = false = true

}