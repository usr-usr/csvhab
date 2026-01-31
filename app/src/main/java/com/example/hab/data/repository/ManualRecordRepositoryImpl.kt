package com.example.hab.data.repository

import com.example.hab.data.database.dao.CategoryDao
import javax.inject.Inject
import javax.inject.Singleton
// domain
import com.example.hab.domain.model.Id
// data
import com.example.hab.data.database.dao.ManualRecordDao
import com.example.hab.data.mapper.toDomain
import com.example.hab.data.mapper.toEntity
import com.example.hab.domain.model.manual.ManualRecord
import com.example.hab.domain.model.manual.ManualRecordDraft
import com.example.hab.domain.repository.ManualRecordRepository

@Singleton
class ManualRecordRepositoryImpl @Inject constructor(
    private val manualRecordDao: ManualRecordDao,
    private val categoryDao: CategoryDao
) : ManualRecordRepository {

    override suspend fun add(manual: ManualRecordDraft) {
        manualRecordDao.insert(
            manual.toEntity()
        )
    }

    override suspend fun findAll(): List<ManualRecord> {
        // 1. 全レコードと全カテゴリを別々に取得
        val recordEntities = manualRecordDao.getAll()
        val categoryEntities = categoryDao.getAll() // CategoryDaoが必要

        // 2. カテゴリをIDで引けるMapにする（高速化）
        val categoryMap = categoryEntities.associateBy { it.id }

        // 3. マッピング時に対応するカテゴリEntityを渡す
        return recordEntities.mapNotNull { recordEntity ->
            val categoryEntity = categoryMap[recordEntity.categoryId]

            // カテゴリが見つかれば toDomain(categoryEntity) を呼ぶ
            categoryEntity?.let { recordEntity.toDomain(it) }
        }
    }

    override suspend fun update(manual: ManualRecord) {
        manualRecordDao.update(
            manual.toEntity()
        )
    }

    override suspend fun remove(id: Id) {
        manualRecordDao.deleteById(id.value)
    }
}
