package com.example.coffeeapp.models

data class SignInState(
    val isSignSuccessful: Boolean = false,
    val signInError : String? = null
)