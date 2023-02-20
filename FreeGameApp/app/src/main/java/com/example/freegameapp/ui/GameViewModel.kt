package com.example.freegameapp.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.freegameapp.data.model.Game
import com.example.freegameapp.data.repository.GameRepository
import com.example.freegameapp.domain.GetAllGamesUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

sealed interface GameUIState{
    data class Success(val games : List<Game>) : GameUIState
    object Error : GameUIState
    object Loading : GameUIState
}

@HiltViewModel
class GameViewModel @Inject constructor(
    private val gamesUserCase: GetAllGamesUserCase
) : ViewModel() {

    private val _games = MutableLiveData<GameUIState>(GameUIState.Loading);
    val games: LiveData<GameUIState> = _games

    init {
        getGames()
    }

    fun getGames() {
        viewModelScope.launch {
            try {
                val lista = gamesUserCase()
                _games.value = GameUIState.Success(lista)
            }catch (e : IOException){
                Log.e("titi", e.toString())
                _games.value = GameUIState.Error
            }
        }
    }

}