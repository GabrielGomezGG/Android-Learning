package com.example.camaraintentexample

import android.app.Activity.RESULT_OK
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.activity.result.contract.ActivityResultContracts.TakePicture
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.core.content.FileProvider
import coil.compose.AsyncImage
import com.example.camaraintentexample.data.ImageEntity
import com.example.camaraintentexample.ui.theme.CamaraIntentExampleTheme
import com.ujizin.camposer.CameraPreview
import com.ujizin.camposer.state.CamSelector
import com.ujizin.camposer.state.rememberCamSelector
import com.ujizin.camposer.state.rememberCameraState
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.util.Objects
import java.util.UUID

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CamaraIntentExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    Camposer()

                    val images by mainViewModel.images.collectAsState()

                    CameraScreen(
                        context = this@MainActivity,
                        images = images,
                        addImage = mainViewModel::addImage,
                    )
                }
            }
        }
    }
}

@Composable
fun CameraScreen(
    context: Context,
    images: List<ImageEntity>,
    addImage: (Uri) -> Unit,
) {

    var uri: Uri? by remember {
        mutableStateOf(null)
    }

    val camera = rememberLauncherForActivityResult(
        contract = TakePicture(),
        onResult = {
            if (it && uri?.path?.isNotEmpty() == true) {
                // do something with the uri
//                mainViewModel.uploadBasicImage(uri!!)
                addImage(uri!!)
            }
        }
    )
    LazyColumn(){
        item{
            Button(onClick = {
                uri = generateUri(context)
                camera.launch(uri)
            }) {
                Text(text = "Camera")
            }
        }
        items(images){
            AsyncImage(
                model = it.uri,
                contentDescription = null,
            )
        }
    }
}

fun generateUri(
    context: Context
): Uri {
    return FileProvider.getUriForFile(
        Objects.requireNonNull(
            context
        ),
        "com.example.camaraintentexample.provider",
        File.createTempFile(UUID.randomUUID().toString(), ".jpg", context.externalCacheDir)
    )
}

@Composable
fun Camposer() {
    val cameraState = rememberCameraState()
    var camSelector by rememberCamSelector(CamSelector.Back)
    CameraPreview(
        cameraState = cameraState,
        camSelector = camSelector,
    ) {
        // Camera Preview UI
//        cameraState.takePicture(contentValues, mediaURI) { result ->
//            /* ... */
//        }
    }
}