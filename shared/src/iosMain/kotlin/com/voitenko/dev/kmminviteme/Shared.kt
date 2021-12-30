package com.voitenko.dev.kmminviteme

import android.content.Context
import co.touchlab.sqliter.DatabaseConfiguration
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import com.squareup.sqldelight.drivers.native.wrapConnection
import com.voitenko.dev.kmminviteme.local.AppDataBase
import com.voitenko.dev.kmminviteme.local.AppDatabaseQueries
import com.voitenko.dev.kmminviteme.local.database
import io.ktor.client.*
import io.ktor.client.engine.ios.*

public actual class Shared private constructor() {
    public actual val db: AppDatabaseQueries = database(DatabaseDriverFactory().createDriver())
    public actual val ktor: HttpClient = KtorFactory.client()

    @ThreadLocal
    public companion object {
        private var _instance: Shared? = null
        public fun instance(context: Context): Shared =
            _instance ?: Shared().apply { _instance = this }
    }
}

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

internal actual object KtorFactory {
    internal actual fun client() = HttpClient(Ios) {
        engine { configureRequest { setAllowsCellularAccess(true) } }
    }
}