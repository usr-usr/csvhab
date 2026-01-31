package com.example.hab.domain.model.csv

class CsvHeader(val columns: List<String>) {

    private val indexMap: Map<String, Int> =
        columns.mapIndexed { index, name ->
            name.trim() to index
        }.toMap()

    fun hasColumn(name: String): Boolean =
        indexMap.containsKey(name)

    fun indexOf(name: String): Int =
        indexMap[name] ?: -1
}
