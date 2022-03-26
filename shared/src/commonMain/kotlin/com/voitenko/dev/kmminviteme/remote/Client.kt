package com.voitenko.dev.kmminviteme.remote

import com.voitenko.dev.kmminviteme.KtorFactory
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

internal object Client {

    fun address() = KtorFactory.client().config {
        install(Logging) {
            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) {
                    println(message)
                }
            }
        }

        install(ContentNegotiation) {
            json(Json {
                isLenient = true
                ignoreUnknownKeys = true
                prettyPrint = true
            })
        }

        defaultRequest {
            host = "api.mapbox.com"
            url { protocol = URLProtocol.HTTPS }
            contentType(ContentType.Application.Json)
            parametersOf(
                "access_token",
                "sk.eyJ1IjoibWF4aW52aXRlbWUiLCJhIjoiY2ttb252bTMxMGhtNTJwcnZlMzZ4dDV0dCJ9.wnvurSWnmv8SGz3K89Sb3A"
            )
        }
    }
}