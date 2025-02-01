@file:OptIn(ExperimentalEncodingApi::class)

package j_details

import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun main() {
    val user = Person()
    user.password = "1234567"
    println(user.password)

    user.creditCard = "4276 0000 0000 0000"
    println(user.creditCard)

    user.a = "1000"
    user.a = "1001"
}

class Person {

    var a: String by myObservable("0") { old, new ->
        println("$old -> $new")
    }

    var password: String = ""
        set(value) {
            println("Setting: new value: $value")
            val encoded = Base64.encode(value.toByteArray())
            println("Setting: encoded value: $encoded")
            field = encoded
        }
        get() {
            println("Getting: encoded value: $field")
            val decoded = String(Base64.decode(field))
            println("Getting: decoded value: $decoded")
            return decoded
        }

    var creditCard: String by EncryptedProperty()
}

class EncryptedProperty : ReadWriteProperty<Any, String> {

    private var encryptedValue: String = ""

    override fun getValue(thisRef: Any, property: KProperty<*>): String {
        println("Getting: encoded value: $encryptedValue")
        val decoded = String(Base64.decode(encryptedValue))
        println("Getting: decoded value: $decoded")
        return decoded
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
        println("Setting: new value: $value")
        val encoded = Base64.encode(value.toByteArray())
        println("Setting: encoded value: $encoded")
        encryptedValue = encoded
    }
}

fun <T> myObservable(initialValue: T, onChange: (oldValue: T, newValue: T) -> Unit) =
    ObservableProperty(initialValue, onChange)

class ObservableProperty<T>(initialValue: T, val onChange: (oldValue: T, newValue: T) -> Unit) :
    ReadWriteProperty<Any, T> {

    private var currentValue: T = initialValue

    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        return currentValue
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        currentValue = value.also { onChange(currentValue, value) }
    }
}
