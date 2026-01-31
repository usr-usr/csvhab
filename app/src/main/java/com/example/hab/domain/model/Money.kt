package com.example.hab.domain.model

@JvmInline
value class Money private constructor(val amount: Int) {

    companion object {
        fun create(amount: Int): Money {
            require(amount >= 0) { "amount must be positive" }
            return Money(amount)
        }
    }
}
