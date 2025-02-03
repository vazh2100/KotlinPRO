package entities

data class Book(val title: String, val year: Int, val genre: String) {

    override fun toString(): String {
        return "Book: $title\nYear: $year\nGenre: $genre"
    }
}
