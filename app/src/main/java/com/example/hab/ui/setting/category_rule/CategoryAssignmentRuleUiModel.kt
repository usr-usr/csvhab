package com.example.hab.ui.setting.category_rule

import com.example.hab.domain.model.category_rule.CategoryAssignmentRule

data class CategoryAssignmentRuleUiModel(
    val rule: CategoryAssignmentRule,
    val isSelected: Boolean = false
) {
    val id get() = rule.id
    val keyword get() = rule.keyword
    val categoryName get() = rule.category.name
}