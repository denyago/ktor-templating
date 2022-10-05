package name.denyago.blog.web

import io.ktor.server.application.* // ktlint-disable no-wildcard-imports
import io.ktor.server.html.respondHtmlTemplate
import io.ktor.server.routing.get // ktlint-disable no-wildcard-imports
import io.ktor.server.routing.routing
import name.denyago.blog.FakeBlog

fun Application.indexPage() {
    routing {
        get("/") {
            call.respondHtmlTemplate(GenericLayout()) {
                header {
                    +"My Crazy Blog"
                }
                content {
                    FakeBlog.posts.forEach { post ->
                        articleTitle { +post.title.value }
                        articleText { +post.content.value }
                        articleAuthor { +post.author.nickname }
                        post.comments.forEach { comm ->
                            comment {
                                commentText { +comm.content.value }
                                commentAuthor { +comm.author.nickname }
                            }
                        }
                    }
                }
            }
        }
    }
}
