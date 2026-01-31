package com.example.hab.domain.usecase

import com.example.hab.domain.model.category_rule.CategoryAssignmentRuleDraft
import com.example.hab.domain.repository.CategoryAssignmentRuleRepository
import javax.inject.Inject

class AddCategoryAssignmentRuleUseCase @Inject constructor(
    private val repository: CategoryAssignmentRuleRepository
) {

    suspend operator fun invoke(draft: CategoryAssignmentRuleDraft) {
        repository.add(draft)
    }
}