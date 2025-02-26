package b_kotlin_serialization_2

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class Gender {
    @SerialName("Male")
    MALE,

    @SerialName("Female")
    FEMALE
}