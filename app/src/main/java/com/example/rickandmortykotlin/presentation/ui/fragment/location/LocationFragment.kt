package com.example.rickandmortykotlin.presentation.ui.fragment.location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortykotlin.R
import com.example.rickandmortykotlin.common.base.BaseFragment
import com.example.rickandmortykotlin.databinding.FragmentLocationBinding
import com.example.rickandmortykotlin.presentation.ui.adapter.LocationAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LocationFragment() :
    BaseFragment<LocationViewModel, FragmentLocationBinding>() {

    private val viewModel: LocationViewModel by viewModel()
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

    override fun initialize() {
        setupCharacterRecycler()
    }

    private fun setupCharacterRecycler() = with(binding) {
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.adapter = locationAdapter.withLoadStateFooter(
            com.example.rickandmortykotlin.presentation.ui.adapter.paging.LoadStateAdapter {
                locationAdapter.retry()
            })

        locationAdapter.addLoadStateListener { loadStates ->
            recyclerView.isVisible = loadStates.refresh is LoadState.NotLoading
        }

        swipeRefresh.setOnRefreshListener {
            locationAdapter.retry()
            swipeRefresh.isRefreshing = false
        }
    }

    override fun setupRequest() {
        lifecycleScope.launch {
            viewModel.fetchLocations().collectLatest {
                this@LocationFragment.lifecycleScope.launch {
                    locationAdapter.submitData(it)
                }
            }
        }
    }

    private fun onItemClickRecyclerItem(id: Int){
        findNavController().navigate(
            LocationFragmentDirections.actionLocationFragmentToLocationDetailFragment(id)
        )
    }
}