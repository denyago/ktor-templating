package name.denyago.blog.web

import io.ktor.server.html.Placeholder
import io.ktor.server.html.PlaceholderList
import io.ktor.server.html.Template
import io.ktor.server.html.TemplatePlaceholder
import io.ktor.server.html.each
import io.ktor.server.html.insert
import kotlinx.html.* // ktlint-disable no-wildcard-imports

class ContentTemplate : Template<FlowContent> {
    val articleAuthor = Placeholder<FlowContent>()
    val articleTitle = Placeholder<FlowContent>()
    val articleText = Placeholder<FlowContent>()
    val comment = PlaceholderList<SECTION, CommentTemplate>()

    override fun FlowContent.apply() {
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

// TODO: Propose a better test to KTor
class LiTemplate : Template<FlowContent> {
    val txt = Placeholder<FlowContent>()
    override fun FlowContent.apply() {
        p { insert(txt) }
    }
}

class MenuTemplate : Template<FlowContent> {
    val item = PlaceholderList<UL, LiTemplate>()
    override fun FlowContent.apply() {
        if (!item.isEmpty()) {
            ul {
                each(item) {
                    insert(LiTemplate()) {
                        insert(it)
                    }
                }
            }
        }
    }
}

class MainTemplate : Template<HTML> {
    val content = Placeholder<HtmlBlockTag>()
    val menu = TemplatePlaceholder<MenuTemplate>()
    override fun HTML.apply() {
        head {
            title { +"Template" }
        }
        body {
            h1 {
                insert(content)
            }
            insert(MenuTemplate(), menu)
        }
    }
}

class MulticolumnTemplate(val main: MainTemplate) : Template<HTML> {
    val column1 = Placeholder<FlowContent>()
    val column2 = Placeholder<FlowContent>()
    override fun HTML.apply() {
        insert(main) {
            menu {
                item { txt { +"One" } }
                item { txt { +"Two" } }
            }
            content {
                div("column") {
                    insert(column1)
                }
                div("column") {
                    insert(column2)
                }
            }
        }
    }
}
