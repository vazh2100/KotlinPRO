package com.vazh2100.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Gender {
    @SerialName("Male")
    MALE,

    @SerialName("Female")
    FEMALE
}