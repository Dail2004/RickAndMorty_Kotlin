package com.example.rickandmortykotlin.ui.fragment.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.rickandmortykotlin.databinding.FragmentCharacterBinding
import com.example.rickandmortykotlin.ui.adapter.CharacterAdapter
import com.example.rickandmortykotlin.ui.adapter.paging.LoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterFragment() : Fragment() {
    private val viewModel: CharacterViewModel by viewModels()
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupRequests()
    }

    private fun initialize() {
        setupCharacterRecycler()
    }

    private fun setupCharacterRecycler() = with(binding) {
        recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        recyclerView.adapter = characterAdapter.withLoadStateFooter(LoadStateAdapter {
                characterAdapter.retry()
            }
        )



        characterAdapter.addLoadStateListener { loadStates ->
            recyclerView.isVisible = loadStates.refresh is LoadState.NotLoading
        }

        swipeRefresh.setOnRefreshListener {
            characterAdapter.retry()
            swipeRefresh.isRefreshing = false
        }
    }

    private fun setupRequests() {
        viewModel.fetchCharacters().observe(requireActivity(), {
            this.lifecycleScope.launch {
                characterAdapter.submitData(it)
            }
        })
    }

    private fun onItemClickRecyclerItem(id: Int) {
        findNavController().navigate(
            CharacterFragmentDirections
                .actionCharacterFragmentToCharacterDetailFragment(id))
    }
}