package com.example.hab.ui.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<M : Any, VB : ViewBinding>(
    protected val binding: VB
) : RecyclerView.ViewHolder(binding.root) {

    abstract fun bind(model: M)
}
