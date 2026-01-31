package com.example.hab.ui.state

import android.util.Log
import com.example.hab.ui.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class UiStateHolder<T> {

    private val _state =
        MutableStateFlow<UiState<T>>(UiState.Idle)

    val state: StateFlow<UiState<T>> =
        _state.asStateFlow()

    // 非同期処理
    suspend fun run(
        block: suspend () -> T
    ) {
        Log.d("UiStateHolder","state:Loading")
        _state.value = UiState.Loading

        runCatching {
            block()
        }.onSuccess {
            Log.d("UiStateHolder","state:Success data: $it")
            _state.value = UiState.Success(it)
        }.onFailure {
            Log.d("UiStateHolder","state:Error")
            _state.value = UiState.Error(
                it.message ?: "Unexpected error"
            )
        }
    }

    // 同期処理
    fun setSuccess(value: T) {
        Log.d("UiStateHolder","update success data: $value")
        _state.value = UiState.Success(value)
    }

    //
    fun current(): T? {
        return (_state.value as? UiState.Success)?.data
    }

    //
    fun clear() {
        Log.d("UiStateHolder", "state:Cleared (Idle)")
        _state.value = UiState.Idle
    }
}

