package com.example.canvaexample

import android.util.Log
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun MainScreen() {
    val lista = listOf(
        Graphics("titi", 100f),
        Graphics("titi2", 140f),
        Graphics("titi3", 205f),
        Graphics("titi4", 120f),
        Graphics("titi5", 50f),
        Graphics("titi5", 95f),
        Graphics("titi5", 30f),
    )
    Grafico(lista)


}

@Composable
fun Grafico(
    lista: List<Graphics>,
) {

    val maxFrecuencia = lista.map { it.frecuencia }.sum()
    val aux1 = 360 / maxFrecuencia
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
        repeat(listaSize) {
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

