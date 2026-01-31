package com.example.hab.domain.usecase

import com.example.hab.domain.model.manual.ManualRecordDraft
import com.example.hab.domain.repository.ManualRecordRepository
import javax.inject.Inject

class AddManualRecordUseCase @Inject constructor(
    private val repository: ManualRecordRepository
) {

    suspend operator fun invoke(draft: ManualRecordDraft) {
        repository.add(draft)
    }
}