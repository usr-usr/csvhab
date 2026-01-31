// path: com/example/hab/data/database/relation/ManualRecordWithCategory.kt
package com.example.hab.data.database.relation

import androidx.room.Embedded
import androidx.room.Relation
import com.example.hab.data.database.entity.ManualRecordEntity
import com.example.hab.data.database.entity.CategoryEntity
import com.example.hab.domain.model.manual.ManualRecord
import com.example.hab.data.mapper.toDomain

data class ManualRecordWithCategory(
    @Embedded val manualRecordEntity: ManualRecordEntity,
    @Relation(
        parentColumn = "categoryId", // ManualRecordEntity側のカラム名
        entityColumn = "id"           // CategoryEntity側の主キー名
    )
    val categoryEntity: CategoryEntity
)

// 拡張関数でドメインモデル変換を簡略化
fun ManualRecordWithCategory.toDomain(): ManualRecord {
    return manualRecordEntity.toDomain(categoryEntity)
}