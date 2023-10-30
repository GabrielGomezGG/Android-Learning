package com.example.sensorsexample

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.sensorsexample.ui.theme.SensorsExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)
        var isShake = false

        sensorManager.registerListener(object : SensorEventListener{
            override fun onSensorChanged(event: SensorEvent?) {
                event.run {
                    var x = this!!.values[0];
                    var y = this.values[1];
                    var z = this.values[2];

                    // Calcula la aceleración total
                    var acceleration = Math.sqrt((x * x + y * y + z * z).toDouble());

                    // Comprueba si la aceleración supera el umbral de agitación
                    if (acceleration > 40f && !isShake) {
                        Log.i("titi", "asdasdsadas")
                        Log.i("titi", acceleration.toString())
                        Toast.makeText(applicationContext, "asdasdsadas", Toast.LENGTH_SHORT).show()
                        isShake = true
                    }
                }
            }

            override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
            }

        }, sensor, SensorManager.SENSOR_DELAY_FASTEST)

        setContent {
            SensorsExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }
            }
        }
    }
}