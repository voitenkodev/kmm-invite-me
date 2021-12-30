package com.voitenko.dev.kmminviteme.local

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.runtime.coroutines.asFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


internal fun database(driver: SqlDriver): AppDatabaseQueries = AppDataBase.invoke(
    driver,
    Adapters.dateAdapter,
    Adapters.statusAdapter
).appDatabaseQueries

public open class BaseLocalApi(internal val database: AppDatabaseQueries) {

    internal fun lastId(): Flow<Long?> =
        database.getLastId().asFlow().map { it.executeAsOneOrNull() }
}