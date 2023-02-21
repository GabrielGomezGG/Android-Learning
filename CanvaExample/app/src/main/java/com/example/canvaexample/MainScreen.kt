package com.example.canvaexample

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@Preview
@Composable
fun MainScreen() {
    val lista = listOf(
        Graphics("titi",100f),
        Graphics("titi2",140f),
        Graphics("titi3",205f),
        Graphics("titi4",120f),
        Graphics("titi5",50f),
    )
    Grafico(lista = lista)

}

@Composable
fun Grafico(
    lista : List<Graphics>
) {

    val maxFrecuencia = lista.map { it.frecuencia }.sum()
    val aux1 =   360/maxFrecuencia
    val gradosList = lista.map { it.frecuencia * aux1 }
    Log.i("tito", gradosList.sum().toString())
    val aux2 = 100 / maxFrecuencia
    val porcentajeList = lista.map { it.frecuencia * aux2 }
    val listaSize = lista.size
    var anterior = 0f
    val colors = listOf(
        Color.Red,
        Color.Blue,
        Color.Green,
        Color.Yellow,
        Color.Magenta,
        Color.Cyan,
        Color.Black,
        Color.Gray,
        Color.DarkGray,
        Color.LightGray
    )

    Canvas(
        modifier = Modifier.fillMaxSize()
    ) {
        repeat(listaSize){
            drawArc(
                color = colors[it],
                startAngle = anterior,
                useCenter = true,
                sweepAngle = gradosList[it],
            )
            anterior += gradosList[it]
        }
    }
}

