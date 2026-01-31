// path: com/example/hab/data/database/dao/CategoryAssignmentRuleDao.kt
package com.example.hab.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.hab.data.database.entity.CategoryAssignmentRuleEntity
import com.example.hab.data.database.relation.CategoryAssignmentRuleWithCategory

@Dao
interface CategoryAssignmentRuleDao {

    // Create
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(rule: CategoryAssignmentRuleEntity): Long

    // Read
    @Query("""
        SELECT * FROM category_assignment_rules ORDER BY id ASC
    """)
    suspend fun getAll(): List<CategoryAssignmentRuleEntity>

    // Update
    @Update
    suspend fun update(rule: CategoryAssignmentRuleEntity)

    @Transaction
    suspend fun updateAll(rules: List<CategoryAssignmentRuleEntity>) {
        rules.forEach { rule ->
            update(rule)
        }
    }

    // Delete
    @Query("DELETE FROM category_assignment_rules WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Transaction
    @Query("SELECT * FROM category_assignment_rules")
    suspend fun getAllWithCategory(): List<CategoryAssignmentRuleWithCategory>

}