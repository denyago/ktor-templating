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
                    FakeBlog.posts.forEach { blogPost ->
                        post {
                            articleTitle { +blogPost.title.value }
                            articleText { +blogPost.content.value }
                            articleAuthor { +blogPost.author.nickname }
                            blogPost.comments.forEach { comm ->
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

        get("/posts") {}

        get("/posts/{posstId}") {}

        get("/authors/{nickname}") {}
    }
}
