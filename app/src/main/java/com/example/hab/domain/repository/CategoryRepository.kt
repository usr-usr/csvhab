package com.example.hab.domain.repository

import com.example.hab.domain.model.Id
import com.example.hab.domain.model.category.Category
import com.example.hab.domain.model.category.CategoryDraft

interface CategoryRepository {

    suspend fun add(draft: CategoryDraft)

    suspend fun findAll(): List<Category>

    suspend fun findById(id: Id): Category?

    suspend fun update(category: Category)

    suspend fun remove(id: Id)

    suspend fun ensureDefaultCategories()
}