package com.example.hab.domain.model.csv

class CsvRow(private val values: List<String>) {

    fun getString(header: CsvHeader, column: String): String {
        val index = header.indexOf(column)
        return if (index >= 0 && index < values.size) {
            values[index]
        } else {
            ""
        }
    }

    fun getInt(header: CsvHeader, column: String): Int {
        return getString(header, column).toIntOrNull()
            ?: throw IllegalArgumentException("数値変換失敗: $column")
    }

    fun getDouble(header: CsvHeader, column: String): Double {
        return getString(header, column).toDoubleOrNull()
            ?: throw IllegalArgumentException("数値変換失敗: $column")
    }

    fun hasColumn(header: CsvHeader, column: String): Boolean {
        return header.hasColumn(column)
    }
}
