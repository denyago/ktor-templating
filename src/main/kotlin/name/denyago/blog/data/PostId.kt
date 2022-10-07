package name.denyago.blog.data

import java.util.*

@JvmInline
value class PostId(val value: UUID) {
    companion object {
        fun next() = PostId(UUID.randomUUID())
    }
}
