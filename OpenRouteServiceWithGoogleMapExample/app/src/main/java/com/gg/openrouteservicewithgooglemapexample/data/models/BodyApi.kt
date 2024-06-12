package com.gg.openrouteservicewithgooglemapexample.data.models

data class BodyApi(
    val locations: List<List<Double>>,
    val resolve_locations: Boolean = true,
    val units: String = "km",
    val metrics: List<String> = listOf("distance"),
)
