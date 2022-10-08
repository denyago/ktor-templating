package name.denyago.blog.web

import kotlinx.html.FlowContent
import kotlinx.html.a
import name.denyago.blog.data.Author
import name.denyago.blog.data.Post

object Helpers {
    object Routes {
        const val START = "/"
        const val POSTS = "/posts"
        const val POST = "/posts/{id}"
        const val AUTHOR = "/authors/{id}"

        fun post(id: String) = POST.replace("{id}", id)
        fun author(id: String) = AUTHOR.replace("{id}", id)
    }

    fun FlowContent.urlFor(author: Author) {
        a(Routes.author(author.slug())) { +"@${author.slug()}" }
    }

    fun FlowContent.urlFor(post: Post, text: String?) {
        val linkText = text ?: post.title.value
        a(Routes.post(post.id.value.toString())) { +linkText }
    }
}
