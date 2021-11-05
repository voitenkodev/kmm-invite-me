//package com.voitenko.dev.invitemekmm.mapping
//
//import com.voitenko.dev.invitemekmm.models.AddressApp
//import com.voitenko.dev.invitemekmm.models.EventApp
//import com.voitenko.dev.sqldelight.database.GetSortedEvents
//
//internal class GetEventsMap : Mapper<GetSortedEvents, EventApp>() {
//
//    override fun map(model: GetSortedEvents): EventApp = EventApp(
//        id = model.event_id,
//        title = model.title,
//        description = model.description,
//        date = model.date,
//        imagePath = model.event_image,
//        address = AddressApp(
//            latitude = model.address_latitude,
//            longitude = model.address_longitude,
//            name = model.address_name
//        )
//    )
//
//    override fun inverseMap(model: EventApp): GetSortedEvents? {
//        TODO("Not yet implemented")
//    }
//}