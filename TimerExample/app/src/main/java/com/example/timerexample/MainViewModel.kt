package com.example.timerexample

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.util.TimerTask

class MainViewModel : ViewModel() {

}

class Titi : TimerTask(){

    var counterFlow = MutableStateFlow(0)

    private var counter = 0
    override fun run() {
        Log.d("Titi", "counter = $counter")
        counter++
        counterFlow = flow {
            emit(counter)
        }
    }

}