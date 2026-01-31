package com.example.hab.ui.base

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding

abstract class BaseDialogFragment<VB : ViewBinding> : DialogFragment() {

    //==============================================================================================
    // ViewBinding
    //==============================================================================================
    private var _binding: VB? = null
    protected val binding: VB
        get() = _binding
            ?: throw IllegalStateException("ViewBinding is not initialized")

    //==============================================================================================
    // abstract
    //==============================================================================================
    protected abstract fun createViewBinding(inflater: LayoutInflater): VB
    protected abstract fun setupView()
    protected abstract fun getTAG(): String

    //==============================================================================================
    // Dialog
    //==============================================================================================
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        _binding = createViewBinding(LayoutInflater.from(requireContext()))

        setupView()

        return AlertDialog.Builder(requireContext())
            .setView(binding.root)
            .create()
    }

    //==============================================================================================
    // Lifecycle
    //==============================================================================================
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //==============================================================================================
    // Utility
    //==============================================================================================
    protected fun showLog(log: String){
        Log.d(getTAG(), log)
    }
    protected fun showToast(message: String){
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }
}
