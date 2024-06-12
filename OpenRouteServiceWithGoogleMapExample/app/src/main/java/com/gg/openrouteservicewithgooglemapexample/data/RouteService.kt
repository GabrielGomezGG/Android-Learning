package com.gg.openrouteservicewithgooglemapexample.data

import com.gg.openrouteservicewithgooglemapexample.BuildConfig
import com.gg.openrouteservicewithgooglemapexample.data.models.BodyApi
import com.gg.openrouteservicewithgooglemapexample.data.models.ResponseApi
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST


interface RouteService {

    @Headers("Authorization: ${BuildConfig.OPEN_ROUTE_SERVICE_API}")
    @POST("/v2/matrix/driving-car")
    suspend fun getDistance(
        @Body bodyApi: BodyApi
    ): Response<ResponseApi>
}