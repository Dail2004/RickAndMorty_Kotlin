package com.example.rickandmortykotlin.ui.fragment.episode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortykotlin.databinding.FragmentCharacterBinding
import com.example.rickandmortykotlin.databinding.FragmentEpisodeBinding
import com.example.rickandmortykotlin.ui.adapter.CharacterAdapter
import com.example.rickandmortykotlin.ui.adapter.EpisodeAdapter
import com.example.rickandmortykotlin.ui.fragment.character.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EpisodeFragment : Fragment() {
    private val viewModel: EpisodeViewModel by viewModels()
    private lateinit var binding: FragmentEpisodeBinding
    private val episodeAdapter = EpisodeAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEpisodeBinding.inflate(inflater, container, false)
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
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.adapter = episodeAdapter.withLoadStateFooter(
            com.example.rickandmortykotlin.ui.adapter.paging.LoadStateAdapter {
                episodeAdapter.retry()
            }
        )

        episodeAdapter.addLoadStateListener { loadStates ->
            recyclerView.isVisible = loadStates.refresh is LoadState.NotLoading
        }


        swipeRefresh.setOnRefreshListener {
            episodeAdapter.retry()
            swipeRefresh.isRefreshing = false
            setupRequests()
        }
    }

    private fun setupRequests() {
        viewModel.fetchEpisode().observe(requireActivity(), {
            this.lifecycleScope.launch {
                episodeAdapter.submitData(it)
            }
        })
    }
}