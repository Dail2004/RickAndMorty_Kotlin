package com.example.rickandmortykotlin.ui.fragment.location

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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortykotlin.databinding.FragmentLocationBinding
import com.example.rickandmortykotlin.ui.adapter.LocationAdapter
import com.example.rickandmortykotlin.ui.fragment.episode.EpisodeFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LocationFragment : Fragment() {
    private val viewModel: LocationViewModel by viewModels()
    private lateinit var binding: FragmentLocationBinding
    private val locationAdapter = LocationAdapter(this::onItemClickRecyclerItem)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLocationBinding.inflate(inflater, container, false)
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
        recyclerView.adapter = locationAdapter.withLoadStateFooter(
            com.example.rickandmortykotlin.ui.adapter.paging.LoadStateAdapter {
                locationAdapter.retry()
            })

        locationAdapter.addLoadStateListener { loadStates ->
            recyclerView.isVisible = loadStates.refresh is LoadState.NotLoading
        }

        swipeRefresh.setOnRefreshListener {
            locationAdapter.retry()
            swipeRefresh.isRefreshing = false
            setupRequests()
        }
    }

    private fun setupRequests() {
        viewModel.fetchLocations().observe(requireActivity(), {
            this.lifecycleScope.launch {
                locationAdapter.submitData(it)
            }
        })
    }
    private fun onItemClickRecyclerItem(id: Int){
        findNavController().navigate(
            LocationFragmentDirections.actionLocationFragmentToLocationDetailFragment(id)
        )
    }
}