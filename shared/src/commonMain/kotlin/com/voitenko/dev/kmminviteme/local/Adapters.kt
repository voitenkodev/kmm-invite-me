package com.voitenko.dev.kmminviteme.local

import com.squareup.sqldelight.ColumnAdapter
import com.voitenko.dev.kmminviteme.local.models.Status
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toLocalDateTime

private const val ACTIVE = "active"
private const val USER = "used"

internal object Adapters {
    internal val statusAdapter = Ticket.Adapter(object : ColumnAdapter<Status, String> {
        override fun decode(databaseValue: String): Status = when (databaseValue) {
            ACTIVE -> Status.ACTIVE
            USER -> Status.USED
            else -> Status.UNKNOWN
        }

        override fun encode(value: Status): String = when (value) {
            Status.ACTIVE -> ACTIVE
            Status.USED -> USER
            else -> ""
        }
    })

    internal val dateAdapter = Event.Adapter(object : ColumnAdapter<LocalDateTime, String> {
        override fun decode(databaseValue: String): LocalDateTime =
            databaseValue.toLocalDateTime()

        override fun encode(value: LocalDateTime): String = value.toString()
    })
}
