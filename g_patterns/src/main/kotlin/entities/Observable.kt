package entities

interface Observable<T> {
    fun addObserver(observer: Observer<T>)
    fun removeObserver(observer: Observer<T>)
    fun notifyObservers(currentValue: T)
}