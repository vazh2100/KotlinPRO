package entities.map

import entities.collection.MyCollection
import entities.set.MySet

interface MyMutableMap<K, V> : MyMap<K, V> {

    override val size: Int
    override val keys: MySet<K>
    override val values: MyCollection<V>

    override operator fun get(key: K): V?
    override fun containsKey(key: K): Boolean
    override fun containsValue(value: V): Boolean

    //возвращает значение, которое лежало по этому ключу до этого
    fun put(key: K, value: V): V?
    operator fun plus(pair: Pair<K, V>) = put(pair.first, pair.second)

    fun remove(key: K): V?
    operator fun minus(key: K) = remove(key)

    fun clear()

}