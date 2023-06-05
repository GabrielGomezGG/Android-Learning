package com.example.sharedpreferencesexample

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.sharedpreferencesexample.ui.theme.SharedPreferencesExampleTheme

class MainActivity : ComponentActivity() {


    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val sharedPref = getSharedPreferences("titi", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        setContent {
            SharedPreferencesExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    var text by remember { mutableStateOf(sharedPref.getString("text", null) ?: "") }

                    Column(
                        Modifier.fillMaxSize(),
                        Arrangement.Center,
                        Alignment.CenterHorizontally
                    ) {
                        TextField(value = text, onValueChange = { text = it })
                        Button(onClick = {
                            algo(sharedPref,editor, text)
                        }) {
                            Text(text = "Save")
                        }
                        Text(text = text)
                    }

                }
            }
        }
    }
}

fun algo(sharedPref: SharedPreferences, editor: SharedPreferences.Editor, text: String) {
    editor.apply{
        putString("text", text )
        apply()
    }
}