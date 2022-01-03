package com.example.rickandmortykotlin.presentation.ui.fragment.character.datailCharacter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.rickandmortykotlin.R
import com.example.rickandmortykotlin.common.base.BaseFragment
import com.example.rickandmortykotlin.databinding.FragmentCharacterDetailBinding
import com.example.rickandmortykotlin.presentation.state.UIState
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterDetailFragment :
    BaseFragment<CharacterDetailViewModel,
            FragmentCharacterDetailBinding>() {

    private val viewModel: CharacterDetailViewModel by viewModel()
    private var _binding: FragmentCharacterDetailBinding? = null
    private val binding get() = _binding!!

    private val args: CharacterDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchCharacter(args.id)
        setupRequests()
    }

    private fun setupRequests() = with(binding) {
        viewModel.characterState.subscribe {
            progressBar.isVisible = it is UIState.Loading
            detailIdGroup.isVisible = it !is UIState.Loading
            when (it){
                is UIState.Error ->{
                    Toast.makeText(requireActivity(), it.massage, Toast.LENGTH_SHORT).show()
                }
                is UIState.Loading -> {

                }
                is UIState.Success ->{
                    it.data.let { data ->
                        name.text = data.name
                        image.load(data.image)
                        status2.text = data.status
                        species.text = data.species
                        url.text = data.url
                        created.text = data.created
                        location.text = data.location.name
                        when (data.status) {
                            "Alive" -> back.setBackgroundResource(R.drawable.alive_green)
                            "Dead" -> back.setBackgroundResource(R.drawable.dead_red)
                            else -> back.setBackgroundResource(R.drawable.unknown_wight)
                        }
                    }
                }
            }
        }
    }


}
