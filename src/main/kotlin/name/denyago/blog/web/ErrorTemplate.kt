package name.denyago.blog.web

import io.ktor.server.html.* // ktlint-disable no-wildcard-imports
import kotlinx.html.* // ktlint-disable no-wildcard-imports

class ErrorTemplate : Template<FlowContent> {
    val text = Placeholder<FlowContent>()

    override fun FlowContent.apply() {
        article("message is-danger") {
            div("message-body") {
                insert(text)
            }
        }
    }
}
