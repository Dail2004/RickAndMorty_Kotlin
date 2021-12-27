package com.example.rickandmortykotlin.ui.fragment.character.datailCharacter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.rickandmortykotlin.R
import com.example.rickandmortykotlin.common.resource.Resource
import com.example.rickandmortykotlin.databinding.FragmentCharacterDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {
    private val viewModel: CharacterDetailViewModel by viewModels()
    private lateinit var binding: FragmentCharacterDetailBinding
    private val args: CharacterDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialise()
        setupObservers()
    }

    private fun initialise() {
            viewModel.fetchCharacter(args.id)
    }

    private fun setupObservers() = with(binding) {
        lifecycleScope.launch {
            viewModel.characterState.collect {
                when (it) {
                    is Resource.Error -> {
                        Toast.makeText(requireActivity(), it.massage, Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        it.data?.let { data ->
                            name.text = data.name
                            image.load(data.image)
                            status2.text = data.status
                            species.text = data.species
                            url.text = data.url
                            created.text = data.created
                            location.text = data.location.name
                            when (data.status) {
                                "Alive" -> statusColor.setBackgroundResource(R.drawable.shape_green)
                                "Dead" -> statusColor.setBackgroundResource(R.drawable.shape_red)
                                else -> statusColor.setBackgroundResource(R.drawable.shape_white)
                            }
                        }
                    }
                }
            }
        }
    }
}