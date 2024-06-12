package com.gg.openrouteservicewithgooglemapexample.data.models

data class ResponseApi(
    val destinations: List<Destination>,
    val distances: List<List<Double>>,
)