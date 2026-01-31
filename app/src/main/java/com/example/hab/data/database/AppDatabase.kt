// path: com/example/hab/data/database/AppDatabase.kt
package com.example.hab.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.hab.data.database.dao.CategoryDao
import com.example.hab.data.database.dao.CategoryAssignmentRuleDao
import com.example.hab.data.database.dao.ManualRecordDao
import com.example.hab.data.database.dao.PayPayRecordDao
import com.example.hab.data.database.entity.CategoryAssignmentRuleEntity
import com.example.hab.data.database.entity.CategoryEntity
import com.example.hab.data.database.entity.ManualRecordEntity
import com.example.hab.data.database.entity.PayPayRecordEntity

@Database(
    entities = [
        ManualRecordEntity::class,
        PayPayRecordEntity::class,
        CategoryEntity::class,
        CategoryAssignmentRuleEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun manualDao(): ManualRecordDao
    abstract fun payPayDao(): PayPayRecordDao

    abstract fun categoryDao(): CategoryDao
    abstract fun categoryAssignmentRuleDao(): CategoryAssignmentRuleDao
}