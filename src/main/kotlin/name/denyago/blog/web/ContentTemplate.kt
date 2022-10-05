package name.denyago.blog.web

import io.ktor.server.html.Placeholder
import io.ktor.server.html.PlaceholderList
import io.ktor.server.html.Template
import io.ktor.server.html.TemplatePlaceholder
import io.ktor.server.html.each
import io.ktor.server.html.insert
import kotlinx.html.FlowContent
import kotlinx.html.HTML
import kotlinx.html.HtmlBlockTag
import kotlinx.html.UL
import kotlinx.html.article
import kotlinx.html.body
import kotlinx.html.div
import kotlinx.html.h1
import kotlinx.html.h2
import kotlinx.html.head
import kotlinx.html.p
import kotlinx.html.title
import kotlinx.html.ul

class ContentTemplate : Template<FlowContent> {
    val articleAuthor = Placeholder<FlowContent>()
    val articleTitle = Placeholder<FlowContent>()
    val articleText = Placeholder<FlowContent>()
    val comment = PlaceholderList<UL, CommentTemplate>()

    override fun FlowContent.apply() {
        article {
            h2 {
                insert(articleTitle)
            }
            p {
                insert(articleText)
            }
            p {
                insert(articleAuthor)
            }
            ul {
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
