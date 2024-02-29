package com.example.coffeeapp.persentation.sign_in

data class SignInState(
    val isSignSuccessful: Boolean = false,
    val signInError : String? = null
)