// path: com/example/hab/ui/setting/category_rule/CategoryAssignmentRuleViewHolder.kt
package com.example.hab.ui.setting.category_rule

import com.example.hab.databinding.ItemCategoryAssignmentRuleBinding
import com.example.hab.domain.model.Id
import com.example.hab.ui.base.BaseViewHolder

class CategoryAssignmentRuleViewHolder(
    binding: ItemCategoryAssignmentRuleBinding,
    private val onCheckClicked: (Id) -> Unit
) : BaseViewHolder<CategoryAssignmentRuleUiModel, ItemCategoryAssignmentRuleBinding>(binding) {

    override fun bind(model: CategoryAssignmentRuleUiModel) {

        binding.keyNote.text = model.keyword

        binding.valueItem.text = model.categoryName

        binding.checkBox.isChecked = model.isSelected

        binding.checkBox.setOnClickListener {
            onCheckClicked(model.id)
        }
    }
}