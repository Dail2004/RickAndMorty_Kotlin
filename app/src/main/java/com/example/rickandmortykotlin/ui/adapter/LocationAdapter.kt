package com.example.rickandmortykotlin.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortykotlin.common.base.BaseComparator
import com.example.rickandmortykotlin.data.network.dto.location.LocationDto
import com.example.rickandmortykotlin.databinding.CharacterItemBinding
import com.example.rickandmortykotlin.databinding.LocationItemBinding

class LocationAdapter : PagingDataAdapter<LocationDto, LocationAdapter.LocationViewHolder>(
    BaseComparator()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        return LocationViewHolder(
            LocationItemBinding.inflate(LayoutInflater.from(parent.context),
                parent, false)
        )
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        getItem(position)?.let {
            holder.onBind(it)
        }
    }

    class LocationViewHolder(
        private val binding: LocationItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: LocationDto) = with(binding) {
            locationName.text = item.name
        }
    }
}