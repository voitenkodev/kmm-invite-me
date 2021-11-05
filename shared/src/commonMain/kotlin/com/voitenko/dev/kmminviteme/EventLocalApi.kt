package com.voitenko.dev.kmminviteme

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.voitenko.dev.kmminviteme.local.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.LocalDateTime

public class EventLocalApi(driver: SqlDriver) : BaseLocalApi(database(driver)) {

    public fun eventsByDate(): Flow<List<GetSortedEvents>> =
        database.getSortedEvents()
            .asFlow()
            .map { it.executeAsList() }

    public fun eventById(id: Long): Flow<GetEventById?> =
        database.getEventById(id)
            .asFlow()
            .map { it.executeAsOneOrNull() }

    public fun eventByQuery(query: String, limit: Long): Flow<List<GetEventsByQuery>> =
        database.getEventsByQuery(query, limit)
            .asFlow()
            .map { it.executeAsList() }

    public fun setEvent(
        title: String, description: String, date: LocalDateTime,
        image: String?, lat: Double?, lng: Double?, name: String
    ): Flow<Long?> =
        flow { emit(database.setEvent(title, description, date, image)) }
            .flatMapConcat { flow { emit(database.setAddress(lat, lng, name)) } }
            .flatMapConcat { lastId() }

    public fun eventWithGuestById(id: Long): Flow<List<GetEventWithGuestsById>?> =
        database.getEventWithGuestsById(id)
            .asFlow()
            .map { it.executeAsList() }

    public fun eventsWithGuests(): Flow<List<GetEventsWithGuests>?> =
        database.getEventsWithGuests()
            .asFlow()
            .map { it.executeAsList() }
}