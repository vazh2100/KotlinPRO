package entities

data class Author(val name: String, val bio: String) {
    override fun toString(): String {
        return "Name: $name\nBio: $bio"
    }
}
