// path: com/example/hab/data/repository/CategoryRepositoryImpl.kt
package com.example.hab.data.repository

import com.example.hab.data.database.dao.CategoryDao
import com.example.hab.data.mapper.toDomain
import com.example.hab.data.mapper.toEntity
import com.example.hab.domain.model.Id
import com.example.hab.domain.model.category.Category
import com.example.hab.domain.model.category.CategoryDraft
import com.example.hab.domain.repository.CategoryRepository // 修正: インターフェース名変更に追従
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryRepositoryImpl @Inject constructor(
    private val dao: CategoryDao
) : CategoryRepository {

    override suspend fun add(draft: CategoryDraft) {
        dao.insert(draft.toEntity())
    }

    override suspend fun findAll(): List<Category> {
        return dao.getAll().map { it.toDomain() }
    }

    override suspend fun findById(id: Id): Category? {
        return dao.getById(id.value)?.toDomain()
    }

    override suspend fun update(category: Category) {
        dao.update(category.toEntity())
    }

    override suspend fun remove(id: Id) {
        dao.deleteById(id.value)
    }

    override suspend fun ensureDefaultCategories() {
        if (dao.getAll().isNotEmpty()) return

        val defaultDrafts = listOf(
            "食費", "日用品費", "医療費", "被服費", "美容費",
            "交際費", "娯楽費", "雑費", "特別費", "住宅費",
            "水道光熱費", "通信費", "保険費", "車両費", "学費",
            "税金", "交通費", "その他", "PayPay"
        ).map { name -> CategoryDraft.create(name) }

        defaultDrafts.forEach {
            dao.insert(it.toEntity())
        }
    }
}