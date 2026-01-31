// path: com/example/hab/di/DatabaseModule.kt
package com.example.hab.di

import android.content.Context
import androidx.room.Room
import com.example.hab.data.database.AppDatabase
import com.example.hab.data.database.dao.CategoryDao
import com.example.hab.data.database.dao.CategoryAssignmentRuleDao
import com.example.hab.data.database.dao.ManualRecordDao
import com.example.hab.data.database.dao.PayPayRecordDao // 不足していたら追加
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "hab_database.db"
        ).build()

    @Provides
    fun provideCategoryDao(
        db: AppDatabase
    ): CategoryDao = db.categoryDao()

    @Provides
    fun provideCategoryAssignmentRuleDao(
        db: AppDatabase
    ): CategoryAssignmentRuleDao = db.categoryAssignmentRuleDao() // 追従

    @Provides
    fun provideManualDao(
        db: AppDatabase
    ): ManualRecordDao = db.manualDao()

    @Provides
    fun providePayPayDao(
        db: AppDatabase
    ): PayPayRecordDao = db.payPayDao()
}