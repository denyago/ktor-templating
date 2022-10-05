package name.denyago.blog

import io.github.serpro69.kfaker.Faker
import name.denyago.blog.data.Author
import name.denyago.blog.data.Comment
import name.denyago.blog.data.CommentContent
import name.denyago.blog.data.Post
import name.denyago.blog.data.PostContent
import name.denyago.blog.data.Title
import kotlin.random.Random.Default.nextInt

object FakeBlog {
    val posts by lazy {
        (0..10).map {
            Post(
                AUTHORS.random(),
                TITLES.random(),
                CONTENTS.random(),
                (0..nextInt(5)).map { Comment(AUTHORS.random(), COMMENTS.random()) }
            )
        }
    }

    private val faker = Faker()
    private val AUTHORS: List<Author> = (0..3).map {
        Author(faker.name.nameWithMiddle(), faker.animal.name())
    }
    private val CONTENTS = (0..10).map {
        PostContent(text(60))
    }
    private val COMMENTS = (0..10).map {
        CommentContent(text(20))
    }
    private val TITLES = (0..10).map {
        Title(text(nextInt(3, 5)))
    }

    private fun text(wordCount: Int) =
        (0..wordCount).joinToString(" ") { faker.lorem.words() }
}
