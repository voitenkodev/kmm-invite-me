package com.voitenko.dev.kmminviteme.local

import com.squareup.sqldelight.db.SqlDriver

internal expect class DatabaseDriverFactory {
    internal fun createDriver(): SqlDriver
}