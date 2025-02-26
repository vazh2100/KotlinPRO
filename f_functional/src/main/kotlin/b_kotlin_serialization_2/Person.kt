package b_kotlin_serialization_2

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

///рекомендуется использовать SerialName везде, чтобы обфускация не помешала работе программы
@Serializable
data class Person(
    @SerialName("id") val id: Int,
    @SerialName("first_name") val firstName: String,
    @SerialName("last_name") val lastName: String,
    @SerialName("age") val age: Int,
    @SerialName("gender") val gender: Gender,
    @SerialName("email") val email: String,
    @SerialName("address") val address: String,
    @SerialName("phone_number") val phoneNumber: String
)
