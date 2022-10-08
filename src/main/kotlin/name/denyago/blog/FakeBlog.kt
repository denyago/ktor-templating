package name.denyago.blog

import io.github.serpro69.kfaker.Faker
import name.denyago.blog.data.* // ktlint-disable no-wildcard-imports
import java.lang.IllegalArgumentException
import java.util.*
import kotlin.random.Random.Default.nextInt

object FakeBlog {
    val posts by lazy {
        (1..10).map {
            Post(
                PostId.next(),
                author(),
                title(),
                postContent(),
                (1..nextInt(1, 10)).map { Comment(author(), commentContent()) }
            )
        }
    }

    private val faker = Faker()
    private fun author() =
        Author(faker.name.nameWithMiddle(), faker.adjective.positive() + " " + faker.animal.name())

    private fun postContent() =
        PostContent(text(60))

    private fun commentContent() =
        CommentContent(text(20))

    private fun title() =
        Title(text(nextInt(3, 5)))

    private fun text(wordCount: Int) =
        (0..wordCount).joinToString(" ") { faker.lorem.words() }

    private fun findPost(id: PostId?): Result<Post> =
        posts
            .find { it.id == id }
            .let {
                if (it != null) Result.success(it) else Result.failure(
                    Errors.NotFoundError.from("Post", id?.value.toString())
                )
            }

    fun findPost(id: String?): Result<Post> =
        try {
            findPost(PostId(UUID.fromString(id)))
        } catch (e: IllegalArgumentException) {
            Result.failure(e)
        }
}
