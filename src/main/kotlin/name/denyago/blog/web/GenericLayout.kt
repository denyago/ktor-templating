package name.denyago.blog.web

import io.ktor.server.html.* // ktlint-disable no-wildcard-imports
import kotlinx.html.* // ktlint-disable no-wildcard-imports

class GenericLayout : Template<HTML> {
    val header = Placeholder<FlowContent>()
    val content = TemplatePlaceholder<ContentTemplate>()
    private val css = listOf(
        "https://cdn.jsdelivr.net/npm/bulma@0.9.4/css/bulma.min.css",
        "https://cdn.jsdelivr.net/npm/@mdi/font@6.9.96/css/materialdesignicons.min.css"
    )
    override fun HTML.apply() {
        head {
            meta(charset = "utf-8")
            meta(name = "viewport", content = "width=device-width, initial-scale=1")
            title("My Crazy Blog")
            css.forEach {
                link(rel = "stylesheet", href = it)
            }
        }
        body {
            section("section") {
                div("container") {
                    insert(ContentTemplate(), content)
                }
            }
        }
    }
}
