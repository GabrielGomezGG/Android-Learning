package com.gg.openrouteservicewithgooglemapexample.data.models

data class Destination(
    val location: List<Double>,
    val name: String,
    val snapped_distance: Double
)