package name.denyago.blog.data

object Errors {
    class NotFoundError(message: String) : Error(message) {
        companion object {
            fun from(clazz: String, id: String?) =
                NotFoundError(
                    "$clazz not found for ID ${id ?: "NULL"}"
                )
        }
    }
}
