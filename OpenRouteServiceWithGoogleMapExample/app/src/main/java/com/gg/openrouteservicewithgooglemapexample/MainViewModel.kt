package com.gg.openrouteservicewithgooglemapexample

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gg.openrouteservicewithgooglemapexample.data.RouteService
import com.gg.openrouteservicewithgooglemapexample.data.models.BodyApi
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (
    private val routeService: RouteService
) : ViewModel(){

    private val burger23 = LatLng(-34.79524392045013,-58.61619886010885)

    private val _distance = MutableStateFlow<Double?>(null)
    val distance = _distance.asStateFlow()

    fun getDistance(destino: LatLng) {
        viewModelScope.launch {
            val wea = routeService.getDistance(BodyApi(
                listOf(
                    listOf(burger23.longitude, burger23.latitude),
                    listOf(destino.longitude, destino.latitude)
                )
            )).body()?.distances?.first()?.get(1)

            _distance.value = wea
        }
    }

}