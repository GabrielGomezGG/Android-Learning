package com.gg.home.ui

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewScreenSizes

@Composable
fun HomeScreen() {
    Text(text = "Home Screen")
}

@PreviewLightDark
//@PreviewFontScale
//@PreviewScreenSizes
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}