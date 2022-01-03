package com.example.rickandmortykotlin.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmortykotlin.common.base.BaseComparator
import com.example.rickandmortykotlin.data.network.dto.location.LocationDto
import com.example.rickandmortykotlin.databinding.LocationItemBinding

class LocationAdapter(
    private val onItemClick: (id: Int) -> Unit,
) : PagingDataAdapter<LocationDto, LocationAdapter.LocationViewHolder>(
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

    inner class LocationViewHolder(
        private val binding: LocationItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                getItem(absoluteAdapterPosition)?.let {
                    onItemClick(it.id)
                }
            }
        }

        fun onBind(item: LocationDto) = with(binding) {
            name.text = item.name
            type.text = item.type
            localed.text = item.dimension
        }
    }
}