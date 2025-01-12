package entities.map

import entities.collection.MyCollection
import entities.set.MySet

interface MyMap<K, V> {
    val size: Int
    val keys: MySet<K>
    val values: MyCollection<V>

    operator fun get(key: K): V?
    fun containsKey(key: K): Boolean
    fun containsValue(value: V): Boolean
}