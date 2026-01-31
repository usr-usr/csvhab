// path: com/example/hab/domain/repository/CategoryAssignmentRuleRepository.kt
package com.example.hab.domain.repository

import com.example.hab.domain.model.Id
import com.example.hab.domain.model.category.Category
import com.example.hab.domain.model.category_rule.CategoryAssignmentRule
import com.example.hab.domain.model.category_rule.CategoryAssignmentRuleDraft

interface CategoryAssignmentRuleRepository {
    // suspend fun function(DomainModel)

    suspend fun add(draft: CategoryAssignmentRuleDraft)

    suspend fun findAll(): List<CategoryAssignmentRule>

    suspend fun update(rule: CategoryAssignmentRule)

    suspend fun updateAll(rules: List<CategoryAssignmentRule>)

    suspend fun remove(id: Id)
}