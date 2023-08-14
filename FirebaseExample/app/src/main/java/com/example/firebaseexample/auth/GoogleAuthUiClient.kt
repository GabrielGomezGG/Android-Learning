package com.example.firebaseexample.auth

import android.content.Context
import com.google.android.gms.auth.api.identity.SignInClient

class GoogleAuthUiClient(
    private val context : Context,
    private val oneTapClient : SignInClient
) {
}