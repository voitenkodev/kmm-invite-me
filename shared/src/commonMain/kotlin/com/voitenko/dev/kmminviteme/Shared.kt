package com.voitenko.dev.kmminviteme

import com.squareup.sqldelight.db.SqlDriver
import com.voitenko.dev.kmminviteme.local.AppDatabaseQueries
import io.ktor.client.*

public expect class Shared {
    public val db: AppDatabaseQueries
    public val ktor: HttpClient
}

internal expect class DatabaseDriverFactory {
    internal fun createDriver(): SqlDriver
}

internal expect object KtorFactory {
    internal fun client(): HttpClient
}