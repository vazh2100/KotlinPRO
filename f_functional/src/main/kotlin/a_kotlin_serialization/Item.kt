package a_kotlin_serialization

import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val id: Int,
    val name: String
)
