package name.denyago.blog.web

import io.ktor.server.html.Placeholder
import io.ktor.server.html.Template
import io.ktor.server.html.insert
import kotlinx.html.* // ktlint-disable no-wildcard-imports

class CommentTemplate : Template<SECTION> {
    val commentText = Placeholder<FlowContent>()
    val commentAuthor = Placeholder<FlowContent>()

    override fun SECTION.apply() {
        div("box") {
            div("content") {
                insert(commentText)
            }
            div("content") {
                p("is-pulled-right") {
                    text("by ")
                    i { insert(commentAuthor) }
                }
            }
        }
    }
}
