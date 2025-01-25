package entities.map

import entities.collection.MyCollection
import entities.set.MySet

interface MyMap<out K, out V> {
    val size: Int
    val keys: MySet<K>
    val values: MyCollection<V>
    val entries: MySet<MyEntry<K, V>>

    operator fun get(key: @UnsafeVariance K): V?
    fun containsKey(key: @UnsafeVariance K): Boolean
    fun containsValue(value: @UnsafeVariance V): Boolean

    interface MyEntry<out K, out V> {
        val key: K
        val value: V
    }
}
