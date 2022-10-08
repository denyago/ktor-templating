package name.denyago // ktlint-disable filename

import io.ktor.server.application.* // ktlint-disable no-wildcard-imports
import io.ktor.server.engine.* // ktlint-disable no-wildcard-imports
import io.ktor.server.netty.* // ktlint-disable no-wildcard-imports
import io.ktor.server.sessions.* // ktlint-disable no-wildcard-imports
import name.denyago.blog.web.* // ktlint-disable no-wildcard-imports

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        install(Sessions) {
            cookie<UserSession>("user_session")
        }

        indexPage()
    }.start(wait = true)
}
