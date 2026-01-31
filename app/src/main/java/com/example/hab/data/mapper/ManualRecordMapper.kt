package com.example.hab.data.mapper

import com.example.hab.data.database.entity.CategoryEntity
import com.example.hab.data.database.entity.ManualRecordEntity
import com.example.hab.domain.model.Date
import com.example.hab.domain.model.Id
import com.example.hab.domain.model.Money
import com.example.hab.domain.model.TransactionType
import com.example.hab.domain.model.category.Category
import com.example.hab.domain.model.manual.ManualRecord
import com.example.hab.domain.model.manual.ManualRecordDraft

fun ManualRecordEntity.toDomain(categoryEntity: CategoryEntity): ManualRecord {
    return ManualRecord.create(
        id = Id.create(id),
        type = if (isIncome) TransactionType.INCOME else TransactionType.EXPENSE,
        date = Date.create(year, month, day),
        category = categoryEntity.toDomain(),
        amount = Money.create(amount),
        note = note
    )
}

fun ManualRecordDraft.toEntity(): ManualRecordEntity =
    ManualRecordEntity(
        categoryId = category.id.value,
        isIncome = type == TransactionType.INCOME,
        year = date.year,
        month = date.month,
        day = date.day,
        amount = amount.amount,
        note = note
    )

fun ManualRecord.toEntity(): ManualRecordEntity =
    ManualRecordEntity(
        id = id.value,
        categoryId = category.id.value,
        isIncome = type == TransactionType.INCOME,
        year = date.year,
        month = date.month,
        day = date.day,
        amount = amount.amount,
        note = note
    )