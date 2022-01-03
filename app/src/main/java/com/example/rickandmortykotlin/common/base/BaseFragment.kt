package com.example.rickandmortykotlin.common.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.example.rickandmortykotlin.presentation.state.UIState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

abstract class BaseFragment<ViewModel :
BaseViewModel, Binding : ViewBinding> :
    Fragment() {

    private lateinit var binding: Binding
    private lateinit var viewModel: ViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListener()
        setupRequest()
        setupObservers()
        swipeRefresh()
    }

    open fun initialize() {}

    open fun setupListener() {}

    open fun setupRequest() {}

    open fun setupObservers() {}

    open fun swipeRefresh() {}

    protected fun <T> StateFlow<UIState<T>>.subscribe(
        state: Lifecycle.State = Lifecycle.State.STARTED,
        action: (UIState<T>) -> Unit
    ) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(state) {
                this@subscribe.collect {
                    action(it)
                }
            }
        }
    }
}