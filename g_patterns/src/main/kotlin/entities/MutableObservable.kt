package entities

class MutableObservable<T>(initialValue: T) : Observable<T> {
    private val observers = mutableListOf<Observer<T>>()
    var currentValue: T = initialValue
        private set

    override fun addObserver(observer: Observer<T>) {
        observers.add(observer)
        observer.onChanged(currentValue)
    }

    override fun removeObserver(observer: Observer<T>) {
        observers.remove(observer)
    }

    override fun notifyObservers(currentValue: T) {
        this.currentValue = currentValue
        observers.forEach { it.onChanged(currentValue) }
    }
}