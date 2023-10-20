package com.example.datastorepreferencesexample

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.datastorepreferencesexample.ui.theme.DataStorePreferencesExampleTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


val Context.dataStore by preferencesDataStore(name = "settings")

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            var title by remember {
                mutableStateOf("")
            }

            var value by remember {
                mutableStateOf("")
            }

            val context = LocalContext.current


            DataStorePreferencesExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(
                        value = value,
                        title = title,
                        onValueChange = { value = it },
                        onSave = {
                            CoroutineScope(Dispatchers.IO).launch {
                                context.dataStore.edit { pref ->
                                    pref[stringPreferencesKey("title")] = value
                                }
                                Log.i("titi", "asdasd")
                            }
                        },
                        onLoad = {
                            CoroutineScope(Dispatchers.IO).launch {
                                context.dataStore.data.map { pref ->
                                    pref[stringPreferencesKey("title")] ?: "titi"
                                }.collect {
                                    title = it
                                    Log.i("titi", it)
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    value: String,
    title: String = "asd",
    onValueChange: (String) -> Unit,
    onSave: () -> Unit,
    onLoad: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier) {
            TextField(
                value = value,
                onValueChange = { onValueChange(it) }
            )
            Button(onClick = { onSave() }) {
                Text(text = "Guardar")
            }
            Button(onClick = { onLoad() }) {
                Text(text = "Leer")
            }
            Text(text = title)
        }
    }
}