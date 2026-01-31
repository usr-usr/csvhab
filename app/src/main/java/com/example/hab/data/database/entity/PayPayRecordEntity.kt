package com.example.hab.data.database.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "payPay_transactions",
    indices = [Index(value = ["payPayId"], unique = true)]
)
data class PayPayRecordEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val payPayId: String,
    val isIncome: Boolean,
    val year: Int,
    val month: Int,
    val day: Int,
    val item: String,
    val amount: Int,
    val note: String?
)
