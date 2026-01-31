package com.example.hab.domain.usecase

import com.example.hab.domain.model.category_rule.CategoryAssignmentRule
import com.example.hab.domain.repository.CategoryAssignmentRuleRepository
import javax.inject.Inject

class GetCategoryAssignmentRulesUseCase @Inject constructor(
    private val repository: CategoryAssignmentRuleRepository
) {

    suspend operator fun invoke(): List<CategoryAssignmentRule> {
        return repository.findAll()
    }
}