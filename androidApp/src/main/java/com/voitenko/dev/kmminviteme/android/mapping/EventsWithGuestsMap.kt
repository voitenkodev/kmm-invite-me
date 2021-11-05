//package com.voitenko.dev.invitemekmm.mapping
//
//import com.voitenko.dev.invitemekmm.models.AddressApp
//import com.voitenko.dev.invitemekmm.models.EventApp
//import com.voitenko.dev.invitemekmm.models.EventWithGuestsApp
//import com.voitenko.dev.invitemekmm.models.GuestApp
//import com.voitenko.dev.sqldelight.database.GetEventsWithGuests
//
//internal class EventsWithGuestsMap : Mapper<List<GetEventsWithGuests>, List<EventWithGuestsApp>>() {
//
//    override fun map(model: List<GetEventsWithGuests>): List<EventWithGuestsApp> =
//        model.groupBy {
//            it.event_id
//        }.map {
//            val event = it.value.first().run {
//                EventApp(
//                    event_id, title, description, date, event_image,
//                    AddressApp(address_latitude, address_longitude, address_name),
//                )
//            }
//            val guests = it.value.mapNotNull {
//                if (it.guest_id != null && it.name != null && it.email != null)
//                    GuestApp(it.guest_id!!, it.name!!, it.email!!, it.phone, it.guest_image) // TODO FIX IT
//                else null
//            }
//            EventWithGuestsApp(event, guests)
//        }.toList()
//
//    override fun inverseMap(model: List<EventWithGuestsApp>): List<GetEventsWithGuests>? {
//        TODO("Not yet implemented")
//    }
//}