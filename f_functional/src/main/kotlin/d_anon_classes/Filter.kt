package d_anon_classes

interface Filter<T : Any> {
    fun suitable(item: T): Boolean
}