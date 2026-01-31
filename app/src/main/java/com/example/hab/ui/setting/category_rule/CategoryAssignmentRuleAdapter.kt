package com.example.hab.ui.setting.category_rule

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.hab.databinding.ItemCategoryAssignmentRuleBinding
import com.example.hab.domain.model.Id
import com.example.hab.ui.base.BaseListAdapter

class CategoryAssignmentRuleAdapter(
    private val onCheckClicked: (Id) -> Unit
) : BaseListAdapter<CategoryAssignmentRuleUiModel, CategoryAssignmentRuleViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryAssignmentRuleViewHolder {
        val binding = ItemCategoryAssignmentRuleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CategoryAssignmentRuleViewHolder(binding, onCheckClicked)
    }

    override fun onBindViewHolder(holder: CategoryAssignmentRuleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DiffCallback =
            object : DiffUtil.ItemCallback<CategoryAssignmentRuleUiModel>() {
                override fun areItemsTheSame(
                    oldItem: CategoryAssignmentRuleUiModel,
                    newItem: CategoryAssignmentRuleUiModel
                ): Boolean = oldItem.id == newItem.id

                override fun areContentsTheSame(
                    oldItem: CategoryAssignmentRuleUiModel,
                    newItem: CategoryAssignmentRuleUiModel
                ): Boolean = oldItem == newItem
            }
    }
}