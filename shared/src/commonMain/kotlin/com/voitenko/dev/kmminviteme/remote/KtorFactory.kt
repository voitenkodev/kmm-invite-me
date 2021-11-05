package com.voitenko.dev.kmminviteme.remote

import io.ktor.client.*

internal expect object KtorFactory {
    internal fun client(): HttpClient
}