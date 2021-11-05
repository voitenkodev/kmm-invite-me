//package com.voitenko.dev.invitemekmm.mapping
//
//import com.voitenko.dev.invitemekmm.models.AddressApp
//import com.voitenko.dev.ktor.models.AddressResponse
//
//internal class AddressResponseMap : Mapper<AddressResponse, AddressApp>() {
//
//    override fun map(model: AddressResponse): AddressApp? {
//        val address = model.features?.firstOrNull()?.properties?.address
//            ?: model.features?.firstOrNull()?.placeName
//        return if (address == null) null
//        else AddressApp(
//            longitude = model.query?.firstOrNull(),
//            latitude = model.query?.lastOrNull(),
//            name = address
//        )
//    }
//
//    override fun inverseMap(model: AddressApp): AddressResponse? {
//        TODO("Not yet implemented")
//    }
//}