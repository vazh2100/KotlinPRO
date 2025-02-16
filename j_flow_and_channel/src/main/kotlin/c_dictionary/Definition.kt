package c_dictionary

import kotlinx.serialization.Serializable

@Serializable()
internal data class Definition(
    val definition: String,
)