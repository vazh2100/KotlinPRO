package entities.map

import entities.collection.MyMutableCollection
import entities.set.MyMutableSet

interface MyMutableMap<K, V> : MyMap<K, V> {

    override val size: Int
    override val keys: MyMutableSet<K>
    override val values: MyMutableCollection<V>
    override val entries: MyMutableSet<MyMutableEntry<K, V>>

    override operator fun get(key: K): V?
    override fun containsKey(key: K): Boolean
    override fun containsValue(value: V): Boolean

    // возвращает значение, которое лежало по этому ключу до этого
    fun put(key: K, value: V): V?
    operator fun plus(pair: Pair<K, V>) = put(pair.first, pair.second)

    fun remove(key: K): V?
    operator fun minus(key: K) = remove(key)

    fun clear()

    interface MyMutableEntry<K, V> : MyMap.MyEntry<K, V> {
        fun setValue(newValue: V): V
    }
}
