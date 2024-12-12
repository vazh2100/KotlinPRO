package entities

//Зависимость от абстракций, а не от реализаций
interface Observer<T> {
    fun onChanged(newValue: T)
}