package name.denyago.blog.data

data class Author(val name: String, val nickname: String) {
    fun slug() = nickname.replace("\\s+".toRegex(), "-")
}
