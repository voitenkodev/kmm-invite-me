package com.voitenko.dev.kmminviteme

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.voitenko.dev.kmminviteme.local.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

public class GuestLocalApi(driver: SqlDriver) : BaseLocalApi(database(driver)) {

    public fun getGuestById(id: Long): Flow<Guest?> =
        database.getGuestById(id)
            .asFlow()
            .map { it.executeAsOneOrNull() }

    public fun setGuest(g: Guest): Flow<Long?> =
        flow { emit(database.setGuest(g)) }
            .flatMapConcat { lastId() }

    public fun getGuests(): Flow<List<Guest>> =
        database.getGuests()
            .asFlow()
            .map { it.executeAsList() }

    public fun getGuestByEmail(email: String): Flow<Guest?> =
        database.getGuestByEmail(email)
            .asFlow()
            .map { it.executeAsOneOrNull() }

    public fun getGuestsByQuery(query: String, limit: Long): Flow<List<Guest>> =
        database.getGuestsByQuery(query, limit)
            .asFlow()
            .map { it.executeAsList() }

    public fun getGuestsWithEvents(): Flow<List<GetGuestsWithEvents>?> =
        database.getGuestsWithEvents()
            .asFlow()
            .map { it.executeAsList() }

    public fun getGuestWithEventById(id: Long): Flow<List<GetGuestWithEventsById>?> =
        database.getGuestWithEventsById(id)
            .asFlow()
            .map { it.executeAsList() }
}