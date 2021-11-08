//package com.voitenko.dev.invitemekmm.mapping
//
//import com.voitenko.dev.invitemekmm.models.AddressApp
//import com.voitenko.dev.invitemekmm.models.EventApp
//import com.voitenko.dev.sqldelight.database.GetEventById
//
//internal class GetEventByIdMap : Mapper<GetEventById, EventApp>() {
//
//    override fun map(model: GetEventById): EventApp = EventApp(
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
//    override fun inverseMap(model: EventApp): GetEventById? {
//        TODO("Not yet implemented")
//    }
//}