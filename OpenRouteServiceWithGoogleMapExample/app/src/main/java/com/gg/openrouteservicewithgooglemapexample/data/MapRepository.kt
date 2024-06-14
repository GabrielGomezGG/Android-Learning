package com.gg.openrouteservicewithgooglemapexample.data

import com.google.android.gms.maps.model.LatLng
import javax.inject.Inject

class MapRepository @Inject constructor(
    private val distanceMatrixService: DistanceMatrixService
){
    fun getDistance(origen: LatLng, destination: LatLng): Double? {
        return distanceMatrixService.getDistance(origen, destination)
    }
}