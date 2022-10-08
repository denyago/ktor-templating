package name.denyago.blog.web

import io.ktor.server.html.* // ktlint-disable no-wildcard-imports
import kotlinx.html.* // ktlint-disable no-wildcard-imports

class CommentTemplate : Template<SECTION> {
    val commentText = Placeholder<FlowContent>()
    val commentAuthor = Placeholder<AuthorTemplate>()

    override fun SECTION.apply() {
        div("box") {
            div("content") {
                insert(commentText)
            }
            div("content") {
                insert(AuthorTemplate()) {
                    insert(commentAuthor)
                }
            }
        }
    }
}
