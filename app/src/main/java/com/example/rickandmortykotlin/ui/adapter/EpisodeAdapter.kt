package com.example.rickandmortykotlin.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortykotlin.common.base.BaseComparator
import com.example.rickandmortykotlin.data.network.dto.episode.EpisodeDto
import com.example.rickandmortykotlin.databinding.EpisodeItemBinding

class EpisodeAdapter : PagingDataAdapter<EpisodeDto, EpisodeAdapter.EpisodeViewHolder>(
    BaseComparator()
) {

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        getItem(position)?.let {
            holder.onBind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        return EpisodeViewHolder(
            EpisodeItemBinding.inflate(LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    class EpisodeViewHolder(
        private val binding: EpisodeItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: EpisodeDto) = with(binding) {
            episodeName.text = item.name
        }
    }
}