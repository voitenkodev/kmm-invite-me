package com.voitenko.dev.kmminviteme

import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.voitenko.dev.kmminviteme.local.BaseLocalApi
import com.voitenko.dev.kmminviteme.local.Ticket
import com.voitenko.dev.kmminviteme.local.database
import com.voitenko.dev.kmminviteme.local.models.Status
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

public class TicketLocalApi(driver: SqlDriver) : BaseLocalApi(database(driver)) {

    public fun setTicket(t: Ticket): Flow<Long?> =
        flow { emit(database.setTicket(t)) }
            .flatMapConcat { lastId() }

    public fun getTicket(eventId: Long, guestId: Long): Flow<Ticket?> =
        database.getTicket(eventId, guestId)
            .asFlow()
            .map { it.executeAsOneOrNull() }

    public fun updateTicket(status: Status, eventId: Long, guestId: Long): Flow<Long?> =
        flow { emit(database.updateTicket(status, eventId, guestId)) }
            .flatMapConcat { lastId() }

    public fun getTicketsByGuestId(guestId: Long): Flow<List<Ticket>> =
        database.getTicketsByGuestId(guestId)
            .asFlow()
            .map { it.executeAsList() }

    public fun getTicketsByEventId(eventId: Long): Flow<List<Ticket>> =
        database.getTicketsByEventId(eventId)
            .asFlow()
            .map { it.executeAsList() }

    public fun getTickets(): Flow<List<Ticket>> =
        database.getTickets()
            .asFlow()
            .map { it.executeAsList() }
}