package co.lobolabs.eshopping

import co.lobolabs.eshopping.features.merchant.domain.route.getMerchantRoute
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.http.content.staticResources
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing

import io.ktor.server.plugins.cors.routing.CORS
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod

fun main() {
    val port = System.getenv("PORT")?.toInt() ?: 8080
    embeddedServer(Netty, port = port, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    install(CORS) {
        allowMethod(HttpMethod.Options)
        allowMethod(HttpMethod.Put)
        allowMethod(HttpMethod.Delete)
        allowMethod(HttpMethod.Patch)
        allowHeader(HttpHeaders.Authorization)
        allowHeader(HttpHeaders.ContentType)
        anyHost() // Em produção, limite aos domínios permitidos
    }

    install(ContentNegotiation) {
        json()
    }

    routing {

        staticResources("/", "dist") {
            default("index.html")
        }

        get("/api/health") {
            call.respondText("API is running!")
        }

        getMerchantRoute()

    }
}
