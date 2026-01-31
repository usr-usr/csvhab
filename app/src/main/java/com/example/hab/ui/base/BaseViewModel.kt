package com.example.hab.ui.base

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.hab.ui.state.UiState

abstract class BaseViewModel: ViewModel() {

    protected abstract fun getTAG(): String
    protected fun showLog(log: String){
        Log.d(getTAG(), log)
    }

}