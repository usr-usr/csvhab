package com.example.hab.data.csv

import com.example.hab.domain.model.csv.CsvTable

sealed class CsvReadResult {
    data class Success(val csvTable: CsvTable) : CsvReadResult()
    data class Error(val message: String) : CsvReadResult()
}
