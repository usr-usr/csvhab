// path: com/example/hab/di/RepositoryModule.kt
package com.example.hab.di

import com.example.hab.data.repository.CategoryAssignmentRuleRepositoryImpl
import com.example.hab.data.repository.CategoryRepositoryImpl
import com.example.hab.data.repository.ManualRecordRepositoryImpl
import com.example.hab.domain.repository.CategoryAssignmentRuleRepository
import com.example.hab.domain.repository.CategoryRepository
import com.example.hab.domain.repository.ManualRecordRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCategoryRepository(
        impl: CategoryRepositoryImpl
    ): CategoryRepository

    @Binds
    @Singleton
    abstract fun bindCategoryAssignmentRuleRepository(
        impl: CategoryAssignmentRuleRepositoryImpl
    ): CategoryAssignmentRuleRepository

    @Binds
    @Singleton
    abstract fun bindManualRepository(
        impl: ManualRecordRepositoryImpl
    ): ManualRecordRepository
}