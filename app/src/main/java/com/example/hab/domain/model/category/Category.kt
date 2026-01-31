package com.example.hab.domain.model.category

import com.example.hab.domain.model.Id

class Category private constructor(
    val id: Id,
    val name: String
) {

    companion object {
        fun create(id: Id, name: String): Category {
            require(name.isNotBlank())
            return Category(id, name)
        }
    }

    override fun toString(): String {
        return name
    }
}