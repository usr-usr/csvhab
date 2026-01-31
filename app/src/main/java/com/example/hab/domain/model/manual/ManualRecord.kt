package com.example.hab.domain.model.manual

import com.example.hab.domain.model.Date
import com.example.hab.domain.model.Id
import com.example.hab.domain.model.Money
import com.example.hab.domain.model.TransactionType
import com.example.hab.domain.model.category.Category

class ManualRecord private constructor(
    val id: Id,
    val type: TransactionType,
    val date: Date,
    val category: Category,
    val amount: Money,
    val note: String?
) {
    companion object {
        fun create(
            id: Id,
            type: TransactionType,
            date: Date,
            category: Category,
            amount: Money,
            note: String?
        ): ManualRecord {
            return ManualRecord(id, type, date, category, amount, note)
        }
    }
}