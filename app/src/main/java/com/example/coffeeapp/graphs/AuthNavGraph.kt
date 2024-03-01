package com.example.coffeeapp.graphs

sealed class AuthScreen(val route : String){
    data object SPLASH : AuthScreen("SPLASH")
    data object Login : AuthScreen("LOGIN")
    data object Registration : AuthScreen("REGISTRATION")
    data object ForgotPassword : AuthScreen("FORGOT-PASSWORD")
}