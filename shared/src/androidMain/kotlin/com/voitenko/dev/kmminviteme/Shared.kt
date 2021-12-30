package com.voitenko.dev.kmminviteme

import android.content.Context
import androidx.sqlite.db.SupportSQLiteDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import com.voitenko.dev.kmminviteme.local.AppDataBase
import com.voitenko.dev.kmminviteme.local.AppDatabaseQueries
import com.voitenko.dev.kmminviteme.local.database
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*

public actual class Shared(context: Context) {
    public actual val db: AppDatabaseQueries =
        database(DatabaseDriverFactory(context).createDriver())
    public actual val ktor: HttpClient = KtorFactory.client()

    public companion object {
        private var _instance: Shared? = null
        public fun instance(context: Context): Shared = _instance ?: Shared(context)
    }
}

internal actual class DatabaseDriverFactory(private val context: Context) {
    internal actual fun createDriver(): SqlDriver = AndroidSqliteDriver(
        AppDataBase.Schema,
        context,
        "InviteMe.db",
        callback = object : AndroidSqliteDriver.Callback(AppDataBase.Schema) {
            override fun onConfigure(db: SupportSQLiteDatabase) {
                super.onConfigure(db)
                db.setForeignKeyConstraintsEnabled(true)
            }
        })
}

internal actual object KtorFactory {
    internal actual fun client() = HttpClient(OkHttp)
}