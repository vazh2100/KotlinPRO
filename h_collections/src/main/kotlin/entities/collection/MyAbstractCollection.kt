package entities.collection

abstract class MyAbstractCollection<T> : MyMutableCollection<T> {

    override fun toString(): String {
        return buildString {
            append("[")
            for (item in this@MyAbstractCollection) {
                append(" ")
                append(item)
                append(",")
            }
            appendLine(" ]")
        }
    }
}
