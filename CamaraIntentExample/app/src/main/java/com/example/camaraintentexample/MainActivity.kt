package com.example.camaraintentexample

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import coil.compose.AsyncImage
import com.example.camaraintentexample.ui.theme.CamaraIntentExampleTheme

class MainActivity : ComponentActivity() {

    private val REQUEST_IMAGE_CAPTURE = 1

    var wea: Bitmap? = null
    var coso : Uri? = Uri.EMPTY

    private val responseLauncher = registerForActivityResult(StartActivityForResult()) {
        if (it.resultCode == RESULT_OK) {
            //return photo
            coso = it.data?.data
            Log.i("titi", coso.toString())
            wea = it.data?.extras?.get("data") as Bitmap
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CamaraIntentExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

                    Column {
                        Button(onClick = {
                            responseLauncher.launch(takePictureIntent)
                        }) {
                            Text(text = "titi")
                        }
                        if(wea != null){
                            Log.i("titi", wea.toString())
                            Image(bitmap = wea!!.asImageBitmap(), contentDescription = null)
                        }
                            AsyncImage(model = coso, contentDescription = null)
                        if(coso != emptyList<Uri>()){
                            Log.i("titi", coso.toString())
                        }
                    }
                }
            }
        }
    }
}