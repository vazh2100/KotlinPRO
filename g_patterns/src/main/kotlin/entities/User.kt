package entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("id") val id: Int,
    @SerialName("first_name") val firstName: String = "",
    @SerialName("last_name") val lastName: String,
    @SerialName("age") val age: Int,
    @SerialName("email") val email: String,
    @SerialName("address") val address: String,
    @SerialName("phone_number") val phoneNumber: String
) {
    constructor(
        firstName: String,
        lastName: String,
        age: Int
    ) : this(id = 0, firstName = firstName, lastName = lastName, age = age, email = "", address = "", phoneNumber = "")


}
