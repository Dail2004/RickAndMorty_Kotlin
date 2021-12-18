package com.example.rickandmortykotlin.common.base

import androidx.recyclerview.widget.DiffUtil

class BaseComparator<T : IBaseDiffModel> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }
}