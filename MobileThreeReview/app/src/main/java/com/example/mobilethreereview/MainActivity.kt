package com.example.mobilethreereview

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.mobilethreereview.ui.MainViewModel
import com.example.mobilethreereview.ui.theme.MobileThreeReviewTheme

class MainActivity : ComponentActivity() {

    private val mainViewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MobileThreeReviewTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val camaraPermission = rememberLauncherForActivityResult(
                        contract = ActivityResultContracts.RequestPermission(),
                        onResult = { isGranted ->
                            mainViewModel.permissionList.put(Manifest.permission.CAMERA, isGranted)
                        }
                    )

                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                        Column() {
                            mainViewModel.permissionList.forEach {
                                Text(text = "Permission: ${it.key}")
                            }
                            Button(onClick = {
                                camaraPermission.launch(
                                    Manifest.permission.CAMERA
                                )
                            }) {
                                Text(text = "Permission Camara")
                            }
                            Button(onClick = {
                                camaraPermission.launch(
                                    Manifest.permission.CALL_PHONE
                                )
                            }) {
                                Text(text = "Permission Call Phone")
                            }
                        }
                    }
                }
            }
        }
    }
}