package com.example.mobilethreereview

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import coil.compose.AsyncImage
import com.example.mobilethreereview.ui.MainViewModel
import com.example.mobilethreereview.ui.theme.MobileThreeReviewTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.currentCoroutineContext

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
//                    PermissionNormal(mainViewModel = mainViewModel)
//                    PermissionAccompanist()
//                    GetContentExample()
                    val context by remember {
                        mutableStateOf(this)
                    }
                    if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                        Text(text = "titi")
                    }else{
                        Text(text = "asdasdasdas")
                    }
//                    Animations()
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun Animations() {

    var expanded by remember {
        mutableStateOf(false)
    }

    Column(modifier = Modifier) {
        Text(
            text = "SDSADsasdadsadsadasdasddsdadsasadadsasdsadsaddsadssadadssdasdasadasdasddadsadsadsadsadsa" +
                    "SDSADsasdadsadsadasdasddsdadsasadadsasdsadsaddsadssadadssdasdasadasdasddadsadsadsadsadsa" +
                    "SDSADsasdadsadsadasdasddsdadsasadadsasdsadsaddsadssadadssdasdasadasdasddadsadsadsadsadsa",
            modifier = Modifier
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 300,
                        delayMillis = 50,
                        easing = LinearOutSlowInEasing
                    )
                )
                .clickable { expanded = !expanded }
            ,
            maxLines = if (!expanded) 1 else 50
        )
    }

}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionAccompanist() {
    val cameraPermission = rememberPermissionState(
        permission = Manifest.permission.ACCESS_COARSE_LOCATION
    )

    val locationsPermissions = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
        )
    )

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            Button(onClick = {
                //cameraPermission.launchPermissionRequest()
                locationsPermissions.launchMultiplePermissionRequest()
            }) {
                Text(text = "Permission")
            }
            Text(text = cameraPermission.status.isGranted.toString())
        }
    }
}

@Composable
fun PermissionNormal(mainViewModel: MainViewModel) {

    val camaraPermission = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            mainViewModel.permissionList.put(Manifest.permission.CAMERA, isGranted)
        }
    )

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
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

@Composable
fun GetContentExample() {
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            imageUri = uri
        }
    Column {
        Button(onClick = { launcher.launch("image/*") }) {
            Text(text = "Load Image")
        }
        AsyncImage(
            model = imageUri,
            contentDescription = "My Image"
        )
    }
}