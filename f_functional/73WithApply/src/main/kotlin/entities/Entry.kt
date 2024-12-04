package com.vazh2100.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Entry(
    @SerialName("value") val value: String,
    @SerialName("description") val description: String,
)