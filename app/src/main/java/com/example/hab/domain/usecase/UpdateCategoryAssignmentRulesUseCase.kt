package com.example.hab.domain.usecase

import com.example.hab.domain.model.category_rule.CategoryAssignmentRule
import com.example.hab.domain.repository.CategoryAssignmentRuleRepository
import javax.inject.Inject

class UpdateCategoryAssignmentRulesUseCase @Inject constructor(
    private val repository: CategoryAssignmentRuleRepository
) {

    suspend operator fun invoke(rules: List<CategoryAssignmentRule>) {
        repository.updateAll(rules)
    }
}