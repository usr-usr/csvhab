package com.example.hab.domain.model.category_rule

import com.example.hab.domain.model.category.Category

class CategoryAssignmentRuleDraft private constructor(
    val keyword: String,
    val category: Category
) {

    companion object {
        fun create(keyword: String, category: Category): CategoryAssignmentRuleDraft {
            require(keyword.isNotBlank())
            return CategoryAssignmentRuleDraft(keyword, category)
        }
    }

    override fun toString(): String {
        return keyword
    }
}