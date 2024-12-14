package entities

class ObservableDelegate<T>(initialValue: T) {
    private val observers = mutableListOf<Observer<T>>()
    private var currentValue: T = initialValue

    fun addObserver(observer: Observer<T>) {
        observers.add(observer)
        observer.onChanged(currentValue)
    }

    fun removeObserver(observer: Observer<T>) {
        observers.remove(observer)
    }

    fun notifyObservers(currentValue: T) {
        this.currentValue = currentValue
        observers.forEach { it.onChanged(currentValue) }
    }
}