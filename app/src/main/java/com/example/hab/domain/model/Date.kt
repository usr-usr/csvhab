package com.example.hab.domain.model

data class Date private constructor(
    val year: Int,
    val month: Int,
    val day: Int
) {
    companion object {
        fun create(year: Int, month: Int, day: Int): Date {
            require(month in 1..12) { "month must be 1..12" }
            require(day in 1..31) { "day must be 1..31" }
            return Date(year, month, day)
        }
    }
}
