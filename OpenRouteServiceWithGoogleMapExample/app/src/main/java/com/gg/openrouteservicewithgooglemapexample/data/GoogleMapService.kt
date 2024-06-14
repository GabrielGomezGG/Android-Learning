package com.gg.openrouteservicewithgooglemapexample.data

import com.gg.openrouteservicewithgooglemapexample.BuildConfig
import com.google.android.gms.maps.model.LatLng
import retrofit2.http.GET

interface GoogleMapService {

    companion object{
        const val BASE_URL = "https://maps.googleapis.com/maps/api"
    }

    @GET("$BASE_URL/geocode/json")
    fun getGeocodingAddress(
        latlng: LatLng,
        key: String = BuildConfig.GOOGLE_MAP_PLATFORM,
        result_type: String = "street_address"
    )

    @GET("$BASE_URL/place/autocomplete/json")
    fun getAutocomplete(
        input: String,
        location: LatLng,
        radius: Int = 20000,
        strictbounds: Boolean = true,
        types: String = "address",
    )
}