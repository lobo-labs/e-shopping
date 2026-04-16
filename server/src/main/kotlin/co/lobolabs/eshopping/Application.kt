package co.lobolabs.eshopping

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.http.content.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    routing {
        // Serve a aplicação Web (Wasm/JS) a partir da pasta resources/dist
        staticResources("/", "dist") {
            default("index.html")
        }

        get("/api") {
            call.respondText("API is running!")
        }
    }
}
