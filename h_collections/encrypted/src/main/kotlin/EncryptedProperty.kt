@file:OptIn(ExperimentalEncodingApi::class)

import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


fun encrypted() = EncryptedProperty()

// internal модификатор в byte code становится public, поэтому для java кода этот конструктор доступен,
// а из Kotlin кода нет.
class EncryptedProperty internal constructor() : ReadWriteProperty<Any, String> {

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