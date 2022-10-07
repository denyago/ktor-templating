package name.denyago.blog.web

import io.ktor.server.html.* // ktlint-disable no-wildcard-imports
import kotlinx.html.* // ktlint-disable no-wildcard-imports

class PostTemplate : Template<SECTION> {
    val articleAuthor = Placeholder<FlowContent>()
    val articleTitle = Placeholder<FlowContent>()
    val articleText = Placeholder<FlowContent>()
    val comment = PlaceholderList<SECTION, CommentTemplate>()

    override fun SECTION.apply() {
        section("section") {
            h1("title") { insert(articleTitle) }
            h2("subtitle") {
                text("by ")
                i { insert(articleAuthor) }
            }
            section("section") {
                div("content") {
                    insert(articleText)
                }
            }
            section("section") {
                each(comment) {
                    // Wow!!! It works!!!
                    // My regards to
                    // https://github.com/liahoo/Cospaii/blob/32eb0a2dc890c58d092da3dc2f0bd6f02341e21a/templates/src/com/cospaii/templates/ProductsCarouselTemplate.kt#L11
                    insert(CommentTemplate()) {
                        insert(it)
                    }
                }
            }
        }
    }
}
