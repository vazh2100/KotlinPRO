package com.vazh2100.entities

import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val id: Int,
    val name: String
)
