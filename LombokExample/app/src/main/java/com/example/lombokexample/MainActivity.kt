package com.example.lombokexample

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.lombokexample.ui.theme.LombokExampleTheme
import com.thinkinglogic.builder.annotation.Builder

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LombokExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val person = PersonBuilder().age(1).name("titi").build()

                    val school = SchoolBuilder().name("titi").build()

                    Log.i("titi", school.toString())
                }
            }
        }
    }
}

@Builder
data class Person(val name: String, val age: Int)

@Builder
data class School(val name: String, val address: String?, val students: List<Person>?)
