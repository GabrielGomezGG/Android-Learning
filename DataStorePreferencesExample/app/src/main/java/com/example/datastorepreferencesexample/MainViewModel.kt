package com.example.datastorepreferencesexample

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataStore : DataStore<Preferences>
): ViewModel() {

    private val _title = MutableStateFlow("")
    val title = _title.asStateFlow()

    init {
        viewModelScope.launch {
            dataStore.data.map {
                it[stringPreferencesKey("title")]
            }.collect{ pref ->
                _title.value = pref.orEmpty()
            }
        }
    }

    fun save(title : String){
        viewModelScope.launch {
            dataStore.edit {
                it[stringPreferencesKey("title")] = title
            }
        }
    }

}