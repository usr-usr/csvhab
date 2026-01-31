package com.example.hab.domain.model.category_rule

import com.example.hab.domain.model.Id
import com.example.hab.domain.model.category.Category // 追加

data class CategoryAssignmentRule private constructor(
    val id: Id,
    val keyword: String,
    val category: Category
) {

    companion object {
        fun create(
            id: Id,
            keyword: String,
            category: Category
        ): CategoryAssignmentRule {
            require(keyword.isNotBlank()) { "Keyword must not be blank" }
            return CategoryAssignmentRule(id, keyword, category)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CategoryAssignmentRule) return false
        return id == other.id && keyword == other.keyword && category == other.category
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + keyword.hashCode()
        result = 31 * result + category.hashCode()
        return result
    }

    override fun toString(): String = keyword
}