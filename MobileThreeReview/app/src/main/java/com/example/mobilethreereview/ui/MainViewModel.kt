package com.example.mobilethreereview.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val visiblePermissionDialogQueue = mutableStateListOf("Titi")

    val permissionList = mutableStateMapOf<String, Boolean>()

    fun dismissDialog(){
        visiblePermissionDialogQueue.removeLast()
    }

    fun onPermissionResult(permission: String, isGranted: Boolean) {
        if (isGranted) {
            visiblePermissionDialogQueue.add(permission)
        }
    }
}