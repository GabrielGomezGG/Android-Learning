package com.gg.webscrapingexample.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.jsoup.Jsoup

class MainViewModel : ViewModel() {

    private val url = "https://es.wikipedia.org/wiki/Kotlin_(lenguaje_de_programaci%C3%B3n)"

    private val _text = MutableStateFlow("Hello, World!")
    val text = _text.asStateFlow()

    init {
        getTextFromUrl()
    }

    private fun getTextFromUrl() {
        viewModelScope.launch(Dispatchers.IO) {

            val document = Jsoup.connect(url).get()
            val elementBody = document
                .body()
                .getElementsByClass("mw-content-ltr mw-parser-output").text()

            _text.value = elementBody

        }
    }

}