package com.voitenko.dev.kmminviteme.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class AddressResponse(
    @SerialName("attribution")
    val attribution: String? = null,
    @SerialName("features")
    val features: List<Feature?>? = null,
    @SerialName("query")
    val query: List<Double?>? = null,
    @SerialName("type")
    val type: String? = null,
    @SerialName("message")
    val message: String? = null
) {

    @Serializable
    public data class Feature(
        @SerialName("center")
        val center: List<Double?>? = null,
        @SerialName("context")
        val context: List<Context?>? = null,
        @SerialName("geometry")
        val geometry: Geometry? = null,
        @SerialName("id")
        val id: String? = null,
        @SerialName("place_name")
        val placeName: String? = null,
        @SerialName("place_type")
        val placeType: List<String?>? = null,
        @SerialName("properties")
        val properties: Properties? = null,
        @SerialName("relevance")
        val relevance: Int? = null,
        @SerialName("text")
        val text: String? = null,
        @SerialName("type")
        val type: String? = null
    ) {
        @Serializable
        public data class Context(
            @SerialName("id")
            val id: String? = null,
            @SerialName("short_code")
            val shortCode: String? = null,
            @SerialName("text")
            val text: String? = null,
            @SerialName("wikidata")
            val wikidata: String? = null
        )

        @Serializable
        public data class Geometry(
            @SerialName("coordinates")
            val coordinates: List<Double?>? = null,
            @SerialName("type")
            val type: String? = null
        )

        @Serializable
        public data class Properties(
            @SerialName("address")
            val address: String? = null,
            @SerialName("category")
            val category: String? = null,
            @SerialName("foursquare")
            val foursquare: String? = null,
            @SerialName("landmark")
            val landmark: Boolean? = null
        )
    }
}
