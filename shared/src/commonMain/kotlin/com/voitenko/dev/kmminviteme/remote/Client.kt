package com.voitenko.dev.kmminviteme.remote

import android.util.Log
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlin.random.Random

internal object Client {

    fun address() = KtorFactory.client().config {
        install(Logging) {
            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) {
                    Log.d("ktor", message)
                }
            }
        }

        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                isLenient = true
                ignoreUnknownKeys = true
                prettyPrint = true
            })
        }

        defaultRequest {
            host = "api.mapbox.com"
            url { protocol = URLProtocol.HTTPS }
            contentType(ContentType.Application.Json)
            parameter(
                "access_token",
                "sk.eyJ1IjoibWF4aW52aXRlbWUiLCJhIjoiY2ttb252bTMxMGhtNTJwcnZlMzZ4dDV0dCJ9.wnvurSWnmv8SGz3K89Sb3A"
            )
        }
    }
}