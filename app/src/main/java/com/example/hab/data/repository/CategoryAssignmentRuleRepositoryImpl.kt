package com.example.hab.data.repository

import com.example.hab.data.database.dao.CategoryAssignmentRuleDao
import com.example.hab.data.database.relation.toDomain
import com.example.hab.data.mapper.toEntity
import com.example.hab.domain.model.Id
import com.example.hab.domain.model.category_rule.CategoryAssignmentRule
import com.example.hab.domain.model.category_rule.CategoryAssignmentRuleDraft
import com.example.hab.domain.repository.CategoryAssignmentRuleRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryAssignmentRuleRepositoryImpl @Inject constructor(
    private val dao: CategoryAssignmentRuleDao,
) : CategoryAssignmentRuleRepository {

    override suspend fun add(draft: CategoryAssignmentRuleDraft) {
        dao.insert(draft.toEntity())
    }

    override suspend fun findAll(): List<CategoryAssignmentRule> {
        // dao.getAllWithCategory() は List<CategoryAssignmentRuleWithCategory> を返す
        val relations = dao.getAllWithCategory()

        // it は CategoryAssignmentRuleWithCategory なので、
        // relation ファイルで定義した toDomain() が呼ばれる
        return relations.map { it.toDomain() }
    }

    override suspend fun update(rule: CategoryAssignmentRule) {
        dao.update(rule.toEntity())
    }

    override suspend fun updateAll(rules: List<CategoryAssignmentRule>) {
        dao.updateAll(
            rules.map { it.toEntity() }
        )
    }

    override suspend fun remove(id: Id) {
        dao.deleteById(id.value)
    }
}