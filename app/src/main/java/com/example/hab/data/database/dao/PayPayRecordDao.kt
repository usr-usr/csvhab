package com.example.hab.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.hab.data.database.entity.PayPayRecordEntity

@Dao
interface PayPayRecordDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entity: PayPayRecordEntity): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(entities: List<PayPayRecordEntity>): List<Long>

    @Query("""
        SELECT * FROM payPay_transactions
        ORDER BY 
            IFNULL(year, 0) DESC,
            IFNULL(month, 0) DESC,
            IFNULL(day, 0) DESC
    """)
    suspend fun getAll(): List<PayPayRecordEntity>

    @Query("DELETE FROM payPay_transactions WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Query("DELETE FROM payPay_transactions")
    suspend fun deleteAll()

    // update
    @Update
    suspend fun update(entity: PayPayRecordEntity)

    @Query("""
        SELECT EXISTS(
            SELECT 1 FROM payPay_transactions
            WHERE payPayId = :payPayId
        )
    """)
    suspend fun existsByPayPayId(payPayId: String): Boolean
}
