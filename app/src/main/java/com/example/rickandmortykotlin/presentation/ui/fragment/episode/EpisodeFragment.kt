package com.example.rickandmortykotlin.presentation.ui.fragment.episode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortykotlin.R
import com.example.rickandmortykotlin.common.base.BaseFragment
import com.example.rickandmortykotlin.databinding.FragmentEpisodeBinding
import com.example.rickandmortykotlin.presentation.ui.adapter.EpisodeAdapter
import com.example.rickandmortykotlin.presentation.ui.adapter.paging.LoadStateAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class EpisodeFragment() :
    BaseFragment<EpisodeViewModel, FragmentEpisodeBinding>() {

    private val viewModel: EpisodeViewModel by viewModel()
    private lateinit var binding: FragmentEpisodeBinding
    private val episodeAdapter = EpisodeAdapter(this::onItemClickRecyclerItem)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentEpisodeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun initialize() {
        setupCharacterRecycler()
    }

    private fun setupCharacterRecycler() = with(binding) {
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.adapter = episodeAdapter.withLoadStateFooter(LoadStateAdapter {
            episodeAdapter.retry()
        }
        )

        episodeAdapter.addLoadStateListener { loadStates ->
            recyclerView.isVisible = loadStates.refresh is LoadState.NotLoading
        }

        swipeRefresh.setOnRefreshListener {
            episodeAdapter.retry()
            swipeRefresh.isRefreshing = false
        }
    }

    override fun setupRequest() {
        lifecycleScope.launch {
            viewModel.fetchEpisodes().collectLatest {
                this@EpisodeFragment.lifecycleScope.launch {
                    episodeAdapter.submitData(it)
                }
            }
        }
    }

    private fun onItemClickRecyclerItem(id: Int) {
        findNavController().navigate(
            EpisodeFragmentDirections.actionEpisodeFragmentToEpisodeDetailFragment(id)
        )
    }
}
