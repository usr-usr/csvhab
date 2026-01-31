package com.example.hab.data.database.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.hab.data.database.entity.CategoryAssignmentRuleEntity
import com.example.hab.data.database.entity.CategoryEntity
import com.example.hab.data.mapper.toDomain
import com.example.hab.domain.model.category_rule.CategoryAssignmentRule

data class CategoryAssignmentRuleWithCategory(
    @Embedded val ruleEntity: CategoryAssignmentRuleEntity,
    @Relation(
        parentColumn = "categoryId",
        entityColumn = "id"
    )
    val categoryEntity: CategoryEntity
)

fun CategoryAssignmentRuleWithCategory.toDomain(): CategoryAssignmentRule {
    return ruleEntity.toDomain(categoryEntity)
}