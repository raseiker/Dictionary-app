package com.example.diccionarityapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.diccionarityapp.R
import com.example.diccionarityapp.data.database.WordEntity
import com.example.diccionarityapp.databinding.FragmentDiccionaryListBinding
import com.example.diccionarityapp.ui.adapter.DictionaryAdapter
import com.example.diccionarityapp.ui.listener.DictionaryItemListener
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DictionaryListFragment : Fragment() {

    private var binding: FragmentDiccionaryListBinding? = null
    private val viewModel: DictionaryViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDiccionaryListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            viewModel = this@DictionaryListFragment.viewModel
            listFragment = this@DictionaryListFragment
            lifecycleOwner = viewLifecycleOwner
            listItemsRv.adapter = DictionaryAdapter(DictionaryItemListener(
                action = { item-> onItemClicked(item) },
                deleteListener = { item -> showDialogDeleteConfirm(item) })
            )
            setFabBehavior(listItemsRv, fabBtn)
        }
        viewModel.getAllTwo()
    }

    /*
    Smartly hide FAB when the recycler view is being scrolled
     */
    private fun setFabBehavior(rv: RecyclerView, fabBtn: FloatingActionButton) {
        rv.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                // if the recycler view is scrolled
                // above hide the FAB
                if (dy > 10 && fabBtn.isShown) {
                    fabBtn.hide()
                }

                // if the recycler view is
                // scrolled above show the FAB
                if (dy < -10 && !fabBtn.isShown) {
                    fabBtn.show()
                }

                // of the recycler view is at the first
                // item always show the FAB
                if (!recyclerView.canScrollVertically(-1)) {
                    fabBtn.show()
                }
            }
        })
    }

    /*
    Executes when FAB is clicked by the user
     */
    fun onFabClicked() {
        viewModel.setUpInsertActionStatus(true)
        val action = DictionaryListFragmentDirections.actionDictionaryListFragmentToAddDictionaryFragment(
            customTitle = getString(R.string.add_fragment_title)
        )
        findNavController().navigate(action)
    }

    /*
    Executes when an recycler view item is clicked by the user
     */
    private fun onItemClicked(item: WordEntity) {
        viewModel.onItemClicked(item)
        val action = DictionaryListFragmentDirections.actionDictionaryListFragmentToAddDictionaryFragment(
            customTitle = getString(R.string.update_fragment_title)
        )
        findNavController().navigate(action)
    }

    /*
    Call view model method in order to delete an item in the database
     */
    private fun onDeleteClicked(item: WordEntity) = viewModel.deleteWord(item)

    /*
    Show alert dialog when the user wants to delete an item
     */
    private fun showDialogDeleteConfirm(item: WordEntity) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(R.string.alert_title)
            .setMessage(getString(R.string.alert_message, item.name.uppercase()))
            .setCancelable(false)
            .setNegativeButton(R.string.alert_negative){_,_ ->
            }
            .setPositiveButton(R.string.alert_positive) {_,_ ->
                onDeleteClicked(item)
            }
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}