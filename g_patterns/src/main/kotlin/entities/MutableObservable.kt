package entities

class MutableObservable<T>(initialValue: T) : Observable<T> {
    private val observers = mutableListOf<Observer<T>>()
    var currentValue: T = initialValue
        set(value) {
            field = value
            notifyObservers()
        }

    override fun addObserver(observer: Observer<T>) {
        observers.add(observer)
        observer.onChanged(currentValue)
    }

    override fun removeObserver(observer: Observer<T>) {
        observers.remove(observer)
    }

    override fun notifyObservers() {
        observers.forEach { it.onChanged(currentValue) }
    }
}