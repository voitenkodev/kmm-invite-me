package com.voitenko.dev.kmminviteme.local

import android.content.Context
import androidx.sqlite.db.SupportSQLiteDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

public actual class DatabaseDriverFactory(private val context: Context) {
    public actual fun createDriver(): SqlDriver = AndroidSqliteDriver(
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