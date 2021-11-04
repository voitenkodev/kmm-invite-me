package com.voitenko.dev.kmminviteme.local

import com.squareup.sqldelight.db.SqlDriver

public expect class DatabaseDriverFactory {
    public fun createDriver(): SqlDriver
}