package name.denyago.blog.web

import io.ktor.server.html.* // ktlint-disable no-wildcard-imports
import kotlinx.html.* // ktlint-disable no-wildcard-imports

class AuthorTemplate : Template<DIV> {
    val name = Placeholder<FlowContent>()
    val link = Placeholder<FlowContent>()

    override fun DIV.apply() {
        div("card") {
            div("card-content") {
                div("media") {
                    div("media-left") {
                        figure("image is-64x64") {
                            img(classes = "is-rounded", src = "https://bulma.io/images/placeholders/64x64.png")
                        }
                    }
                    div("media-content") {
                        p("title is-4") { insert(name) }
                        p("subtitle is-6") { insert(link) }
                    }
                }
            }
        }
    }
}
