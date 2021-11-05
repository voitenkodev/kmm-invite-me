package com.voitenko.dev.kmminviteme.local

import co.touchlab.sqliter.DatabaseConfiguration
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import com.squareup.sqldelight.drivers.native.wrapConnection

internal actual class DatabaseDriverFactory {
    internal actual fun createDriver(): SqlDriver {
        val dbConfig = DatabaseConfiguration(
            name = "InviteMe.db",
            version = AppDataBase.Schema.version,
            extendedConfig = DatabaseConfiguration.Extended(
                foreignKeyConstraints = true
            ),
            create = { connection ->
                wrapConnection(connection) { AppDataBase.Schema.create(it) }
            },
        )
        return NativeSqliteDriver(dbConfig)
    }
}