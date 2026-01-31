package com.example.hab.data.csv

data class CsvImportResult(
    val inserted: Int,
    val skipped: Int,
    val failed: Int,
    val errors: List<String>
)
