package com.example.hab.ui.util

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.hab.ui.state.UiState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

fun <T> LifecycleOwner.observeUiState(
    stateFlow: StateFlow<UiState<T>>,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
    onIdle: (() -> Unit)? = null,
    onLoading: (() -> Unit)? = null,
    onSuccess: (T) -> Unit,
    onError: (String) -> Unit
) {
    lifecycleScope.launch {
        repeatOnLifecycle(minActiveState) {
            stateFlow.collect { state ->
                when (state) {

                    UiState.Idle -> {
                        onIdle?.invoke()
                    }

                    UiState.Loading -> {
                        onLoading?.invoke()
                    }

                    is UiState.Success -> {
                        onSuccess(state.data)
                    }

                    is UiState.Error -> {
                        onError(state.message)
                    }
                }
            }
        }
    }
}
