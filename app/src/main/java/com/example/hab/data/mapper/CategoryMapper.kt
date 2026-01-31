package com.example.hab.data.mapper

import com.example.hab.data.database.entity.CategoryEntity
import com.example.hab.domain.model.Id
import com.example.hab.domain.model.category.Category
import com.example.hab.domain.model.category.CategoryDraft

// Entity -> Domain
fun CategoryEntity.toDomain(): Category {
    return Category.create(
        id = Id.create(id),
        name = categoryName
    )
}

// Domain -> Entity
fun CategoryDraft.toEntity(): CategoryEntity =
    CategoryEntity(
        categoryName = name
    )

// Domain -> Entity
fun Category.toEntity(): CategoryEntity =
    CategoryEntity(
        id = id.value,
        categoryName = name
    )