package com.example.hab.domain.model.manual

import com.example.hab.domain.model.Date
import com.example.hab.domain.model.Money
import com.example.hab.domain.model.TransactionType
import com.example.hab.domain.model.category.Category

class ManualRecordDraft private constructor(
    val type: TransactionType,
    val date: Date,
    val category: Category,
    val amount: Money,
    val note: String?
) {

    companion object {
        fun create(
            type: TransactionType,
            date: Date,
            category: Category,
            amount: Money,
            note: String?
        ): ManualRecordDraft {
            return ManualRecordDraft(type, date, category, amount, note)
        }
    }
}