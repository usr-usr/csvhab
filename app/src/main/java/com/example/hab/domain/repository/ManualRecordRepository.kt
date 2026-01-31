package com.example.hab.domain.repository

import com.example.hab.domain.model.Id
import com.example.hab.domain.model.manual.ManualRecord
import com.example.hab.domain.model.manual.ManualRecordDraft

interface ManualRecordRepository {

    suspend fun add(draft: ManualRecordDraft)

    suspend fun findAll(): List<ManualRecord>

    suspend fun update(record: ManualRecord)

    suspend fun remove(id: Id)
}