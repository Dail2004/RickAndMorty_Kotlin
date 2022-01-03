package com.example.rickandmortykotlin.presentation.ui.fragment.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.rickandmortykotlin.common.base.BaseFragment
import com.example.rickandmortykotlin.databinding.FragmentCharacterBinding
import com.example.rickandmortykotlin.presentation.ui.adapter.CharacterAdapter
import com.example.rickandmortykotlin.presentation.ui.adapter.paging.LoadStateAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterFragment() :
    BaseFragment<CharacterViewModel, FragmentCharacterBinding>() {

    private val viewModel: CharacterViewModel by viewModel()
    private lateinit var binding: FragmentCharacterBinding
    private val characterAdapter = CharacterAdapter(this::onItemClickRecyclerItem)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCharacterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initialize() {
        setupCharacterRecycler()
    }

    private fun setupCharacterRecycler() = with(binding) {
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        recyclerView.adapter = characterAdapter.withLoadStateFooter(LoadStateAdapter {
                characterAdapter.retry()
            }
        )

        swipeRefresh.setOnRefreshListener {
            characterAdapter.retry()
            swipeRefresh.isRefreshing = false
        }

        characterAdapter.addLoadStateListener { loadStates ->
            recyclerView.isVisible = loadStates.refresh is LoadState.NotLoading
            progressBar.isVisible = loadStates.refresh is LoadState.Loading
        }
    }

    override fun setupRequest() {
        lifecycleScope.launch {
            viewModel.fetchCharacters().collectLatest {
                this@CharacterFragment.lifecycleScope.launch {
                    characterAdapter.submitData(it)
                }
            }
        }
    }

    private fun onItemClickRecyclerItem(id: Int) {
        findNavController().navigate(
            CharacterFragmentDirections
                .actionCharacterFragmentToCharacterDetailFragment(id))
    }
}