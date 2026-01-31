package com.example.hab.domain.usecase

import com.example.hab.domain.model.category.Category
import com.example.hab.domain.repository.CategoryRepository
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val repository: CategoryRepository
) {
    suspend operator fun invoke(): List<Category> {
        return repository.findAll()
    }
}