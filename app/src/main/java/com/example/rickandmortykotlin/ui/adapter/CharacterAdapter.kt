package com.example.rickandmortykotlin.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.rickandmortykotlin.common.base.BaseComparator
import com.example.rickandmortykotlin.data.network.dto.character.CharacterDto
import com.example.rickandmortykotlin.databinding.CharacterItemBinding

class CharacterAdapter : PagingDataAdapter<CharacterDto, CharacterAdapter.CharacterViewHolder>(
    BaseComparator()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            CharacterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        getItem(position)?.let {
            holder.onBind(it)
        }
    }

    class CharacterViewHolder(
        private val binding: CharacterItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: CharacterDto) = with(binding) {
            titleIm.load(item.image)
            characterName.text = item.name
        }
    }
}