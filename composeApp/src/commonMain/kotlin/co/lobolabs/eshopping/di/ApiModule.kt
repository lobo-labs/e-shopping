package co.lobolabs.eshopping.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging

internal object ApiModule {
    val client = HttpClient {
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    println("KTOR_CLIENT: $message")
                }
            }
            level = LogLevel.ALL
        }
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
                useAlternativeNames = true
            })
        }
    }
}