package name.denyago.blog.web

import io.ktor.server.application.* // ktlint-disable no-wildcard-imports
import io.ktor.server.html.respondHtmlTemplate
import io.ktor.server.response.* // ktlint-disable no-wildcard-imports
import io.ktor.server.routing.get // ktlint-disable no-wildcard-imports
import io.ktor.server.routing.routing
import io.ktor.server.sessions.* // ktlint-disable no-wildcard-imports
import name.denyago.blog.FakeBlog
import name.denyago.blog.web.Helpers.urlFor

fun Application.indexPage() {
    routing {
        get(Helpers.Routes.START) {
            val error = call.sessions.get<UserSession>()?.error
            call.sessions.clear<UserSession>()

            call.respondHtmlTemplate(GenericLayout()) {
                header {
                    +"My Crazy Blog"
                }
                content {
                    if (error != null) {
                        displayError { text { +error } }
                    }
                    FakeBlog.posts.forEach { blogPost ->
                        post {
                            articleTitle { +blogPost.title.value }
                            articleText { +blogPost.content.value }
                            articleAuthor { +blogPost.author.nickname }
                            blogPost.comments.take(1).forEach { comm ->
                                comment {
                                    commentText { +comm.content.value }
                                    commentAuthor {
                                        name { +comm.author.name }
                                        link { urlFor(comm.author) }
                                    }
                                }
                            }
                            if (blogPost.comments.size > 1) {
                                showMore { urlFor(blogPost, text = "More comments ...") }
                            }
                        }
                    }
                }
            }
        }

        get(Helpers.Routes.POSTS) {}

        get(Helpers.Routes.POST) {
            FakeBlog
                .findPost(call.parameters["id"])
                .onFailure {
                    call.sessions.set(UserSession(it.message))
                    call.respondRedirect(Helpers.Routes.START)
                }
                .onSuccess { blogPost ->
                    call.respondHtmlTemplate(GenericLayout()) {
                        header {
                            +"My Crazy Blog: ${blogPost.title.value}"
                        }
                        content {
                            post {
                                articleTitle { +blogPost.title.value }
                                articleText { +blogPost.content.value }
                                articleAuthor { +blogPost.author.nickname }
                                blogPost.comments.forEach { comm ->
                                    comment {
                                        commentText { +comm.content.value }
                                        commentAuthor {
                                            name { +comm.author.name }
                                            link { urlFor(comm.author) }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
        }

        get(Helpers.Routes.AUTHOR) {}
    }
}
