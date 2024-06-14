package com.gg.openrouteservicewithgooglemapexample

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gg.openrouteservicewithgooglemapexample.ui.theme.OpenRouteServiceWithGoogleMapExampleTheme
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    private val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OpenRouteServiceWithGoogleMapExampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->


                    val distance by mainViewModel.distance.collectAsState()

                    var marker by rememberSaveable {
                        mutableStateOf(LatLng(-34.794611, -58.615703))
                    }

                    val cameraPositionState = rememberCameraPositionState {
                        position = CameraPosition.fromLatLngZoom(marker, 15f)
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        GoogleMap(
                            modifier = Modifier
                                .fillMaxSize(),
                            cameraPositionState = cameraPositionState,
                            onMapClick = {
                                marker = it
                            }
                        ) {
                            Marker(
                                state = MarkerState(position = marker),
                                title = "titulo del lugar",
                            )
                        }

                        Button(
                            onClick = {
                                marker = cameraPositionState.position.target
//                                Log.i("titi", "marker: $marker")
                                mainViewModel.getDistance(marker)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.BottomCenter)
                                .padding(16.dp)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(text = distance.toString())
                            }
                        }
                    }


                }
            }
        }
    }
}

//@Composable
//fun MyMapp(
//    modifier: Modifier = Modifier,
//    cameraPositionState: CameraPositionState,
//) {
//    GoogleMap(
//        modifier = modifier,
//        cameraPositionState = cameraPositionState,
//    ) {
//        Marker(
//            state = MarkerState(position = cameraPositionState.position.target),
//            title = "titulo del lugar",
//        )
//    }
//}

