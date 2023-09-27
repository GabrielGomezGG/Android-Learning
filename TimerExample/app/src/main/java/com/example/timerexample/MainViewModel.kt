package com.example.timerexample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _count = MutableStateFlow(0)
    val count: Flow<Int> = _count.asStateFlow()

    private val _active = MutableStateFlow(true)
    val active: Flow<Boolean> = _active.asStateFlow()

    val tutu = Tutu()

    init {
        viewModelScope.launch {
            tutu.countFlow.collect {
                _count.value = it
            }

            tutu.activeFlow.collect {
                _active.value = it
            }
        }
    }

    fun stopFlow() {
        tutu.stop()
    }

}

class Tutu {

    private var active = true

    private var count = 0

    var countFlow = flow {
        while (active) {
            count++
            emit(count)
            delay(1000)
        }
    }

    var activeFlow = flow {
        emit(active)
    }

    fun stop() {
        active = !active
    }

}