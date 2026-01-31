// path: com/example/hab/data/database/dao/CategoryDao.kt
package com.example.hab.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.hab.data.database.entity.CategoryEntity // 修正: 適切なEntityをインポート

@Dao
interface CategoryDao {

    // Create
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(category: CategoryEntity): Long

    // Read
    @Query("""
        SELECT * FROM categories ORDER BY id ASC
    """)
    suspend fun getAll(): List<CategoryEntity>

    @Query("""
        SELECT * FROM categories
        WHERE id = :id
    """)
    suspend fun getById(id: Int): CategoryEntity?

    // Update
    @Update
    suspend fun update(category: CategoryEntity)

    // Delete
    @Query("DELETE FROM categories WHERE id = :id")
    suspend fun deleteById(id: Int)
}