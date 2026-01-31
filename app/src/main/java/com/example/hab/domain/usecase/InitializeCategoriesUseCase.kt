package com.example.hab.domain.usecase

import com.example.hab.domain.repository.CategoryRepository
import javax.inject.Inject

class InitializeCategoriesUseCase @Inject constructor(
    private val repository: CategoryRepository
) {

    suspend operator fun invoke() {
        repository.ensureDefaultCategories()
    }
}