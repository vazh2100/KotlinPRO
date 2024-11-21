package com.vazh2100.entities

import kotlinx.serialization.Serializable

@Serializable
data class Book(
    val name: String,
    val author: String,
    val year: Int
) {
}
