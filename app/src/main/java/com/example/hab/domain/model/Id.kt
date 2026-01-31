package com.example.hab.domain.model

@JvmInline
value class Id private constructor(val value: Int) {

    companion object {
        fun create(value: Int): Id {
            require(value >= 0) { "Id must be >= 0" }
            return Id(value)
        }
    }
}