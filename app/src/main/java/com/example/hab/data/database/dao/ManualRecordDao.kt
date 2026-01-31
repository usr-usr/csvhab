package com.example.hab.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.hab.data.database.entity.ManualRecordEntity

@Dao
interface ManualRecordDao {

    // CRUD
    // Create
    @Insert(onConflict = OnConflictStrategy.REPLACE)// 上書き
    suspend fun insert(manual: ManualRecordEntity)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(manuals: List<ManualRecordEntity>): List<Long>

    // Read
    @Query("SELECT * FROM manual_transactions ORDER BY year DESC, month DESC, day DESC")
    suspend fun getAll(): List<ManualRecordEntity>

    // delete
    @Query("DELETE FROM manual_transactions WHERE id = :id")
    suspend fun deleteById(id: Int)

    // update
    @Update
    suspend fun update(entity: ManualRecordEntity)

    // 重複確認
    @Query("""
    SELECT EXISTS(
        SELECT 1 FROM manual_transactions
        WHERE year = :year AND month = :month AND day = :day AND amount = :amount
        )
    """)
    suspend fun exists(year: Int, month: Int, day: Int, amount: Int): Boolean
}
