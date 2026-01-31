package com.example.hab.ui.base

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class BaseListAdapter<M : Any, VH : RecyclerView.ViewHolder>(
    diffCallback: DiffUtil.ItemCallback<M>
) : ListAdapter<M, VH>(diffCallback)
