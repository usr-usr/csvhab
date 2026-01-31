// path: com/example/hab/data/mapper/CategoryAssignmentRuleMapper.kt
package com.example.hab.data.mapper

import com.example.hab.data.database.entity.CategoryAssignmentRuleEntity
import com.example.hab.data.database.entity.CategoryEntity
import com.example.hab.domain.model.Id
import com.example.hab.domain.model.category_rule.CategoryAssignmentRule
import com.example.hab.domain.model.category_rule.CategoryAssignmentRuleDraft

fun CategoryAssignmentRuleEntity.toDomain(categoryEntity: CategoryEntity): CategoryAssignmentRule {
    return CategoryAssignmentRule.create(
        id = Id.create(this.id),
        keyword = this.keyword,
        category = categoryEntity.toDomain()
    )
}

fun CategoryAssignmentRuleDraft.toEntity(): CategoryAssignmentRuleEntity =
    CategoryAssignmentRuleEntity(
        keyword = keyword,
        categoryId = category.id.value
    )

fun CategoryAssignmentRule.toEntity(): CategoryAssignmentRuleEntity =
    CategoryAssignmentRuleEntity(
        id = id.value,
        keyword = keyword,
        categoryId = category.id.value
    )