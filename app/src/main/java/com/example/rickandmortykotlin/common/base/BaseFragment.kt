package com.example.rickandmortykotlin.common.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

abstract class BaseFragment<Binding> : Fragment() {
    protected var binding: Binding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initialize()
        setupRequests()
        setupObservers()
        setupListeners()
    }

    protected open fun initialize() {}

    protected open fun setupRequests() {}

    protected open fun setupObservers() {}

    protected open fun setupListeners() {}

}