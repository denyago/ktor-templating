package name.denyago.blog.web

import io.ktor.server.html.Placeholder
import io.ktor.server.html.Template
import io.ktor.server.html.insert
import kotlinx.html.FlowContent
import kotlinx.html.UL
import kotlinx.html.li
import kotlinx.html.p
import kotlinx.html.span

class CommentTemplate : Template<UL> {
    val commentText = Placeholder<FlowContent>()
    val commentAuthor = Placeholder<FlowContent>()

    override fun UL.apply() {
        li {
            p {
                insert(commentText)
                span {
                    text(" by ")
                    insert(commentAuthor)
                }
            }
        }
    }
}
