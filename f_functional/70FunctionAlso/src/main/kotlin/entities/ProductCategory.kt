package com.vazh2100.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ProductCategory {
    @SerialName("Electronics")
    ELECTRONICS,

    @SerialName("Clothing")
    CLOTHING,

    @SerialName("Home Decor")
    HOME_DECOR,

    @SerialName("Beauty")
    BEAUTY,

    @SerialName("Sports")
    SPORTS,

    @SerialName("Books")
    BOOKS,

    @SerialName("Toys")
    TOYS
}