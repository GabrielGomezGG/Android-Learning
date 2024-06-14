package com.gg.openrouteservicewithgooglemapexample.data

import com.google.android.gms.maps.model.LatLng

interface DistanceMatrixService {

    fun getDistance(origen: LatLng, destination: LatLng): Double?

}

class ORSDistanceMatrixService : DistanceMatrixService {

    override fun getDistance(origen: LatLng, destination: LatLng): Double? {
        return 0.0
    }

}

class GoogleMapsDistanceMatrixService : DistanceMatrixService {

    override fun getDistance(origen: LatLng, destination: LatLng): Double? {
        return 0.0
    }

}