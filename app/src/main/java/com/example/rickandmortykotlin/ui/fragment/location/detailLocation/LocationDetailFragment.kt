package com.example.rickandmortykotlin.ui.fragment.location.detailLocation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.rickandmortykotlin.common.resource.Resource
import com.example.rickandmortykotlin.databinding.FragmentLocationDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LocationDetailFragment : Fragment() {
    private val viewModel: LocationDetailViewModel by viewModels()
    private lateinit var binding: FragmentLocationDetailBinding
    private val args: LocationDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentLocationDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialise()
        setupObserve()
    }

    private fun initialise() {
        viewModel.fetchLocation(args.id)
    }

    private fun setupObserve() = with(binding) {
        lifecycleScope.launch {
            viewModel.locationState.collect {
                when (it) {
                    is Resource.Error -> {
                        Toast.makeText(requireActivity(), it.massage, Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Loading -> {

                    }
                    is Resource.Success -> {
                        it.data?.let { data ->
                            name.text = data.name
                            type.text = data.type
                            dimension.text = data.dimension
                            created.text = data.created
                            url.text = data.url
                        }
                    }
                }
            }
        }
    }
}