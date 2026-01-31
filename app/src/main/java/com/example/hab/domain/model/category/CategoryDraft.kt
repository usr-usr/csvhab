package com.example.hab.domain.model.category

class CategoryDraft private constructor(
    val name: String
) {

    companion object {
        fun create(name: String): CategoryDraft {
            require(name.isNotBlank())
            return CategoryDraft(name)
        }
    }

    override fun toString(): String {
        return name
    }
}