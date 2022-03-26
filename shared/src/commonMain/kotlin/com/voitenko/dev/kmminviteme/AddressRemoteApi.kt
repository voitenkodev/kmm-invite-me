package com.voitenko.dev.kmminviteme

import com.voitenko.dev.kmminviteme.remote.Client
import com.voitenko.dev.kmminviteme.remote.models.AddressResponse
import io.ktor.client.*
import io.ktor.client.request.*

public class AddressRemoteApi(private val service: HttpClient = Client.address()) {

//    public suspend fun getAddressByLatLng(lat: Double, lng: Double): AddressResponse? = TODO FIX
//        service.get {
//            url {
//                path("geocoding/v5/mapbox.places/${lng},${lat}.json")
//                parameter("limit", "1")
//            }
//        }
}