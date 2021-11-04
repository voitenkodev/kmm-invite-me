package com.voitenko.dev.kmminviteme.local

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.runtime.coroutines.asFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal fun database(driver: SqlDriver) = AppDataBase.invoke(
    driver,
    Adapters.dateAdapter,
    Adapters.statusAdapter
).appDatabaseQueries

public open class BaseLocalApi(protected val database: AppDatabaseQueries) {

    public fun lastId(): Flow<Long?> = database.getLastId().asFlow().map { it.executeAsOneOrNull() }
}