package com.example.coffeeapp.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.coffeeapp.ui.screens.SplashScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController){
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.SPLASH.route
    ){
        composable(route = AuthScreen.SPLASH.route){
           SplashScreen(navController = navController)
        }
    }
}
sealed class AuthScreen(val route : String){
    data object SPLASH : AuthScreen("SPLASH")
    data object Login : AuthScreen("LOGIN")
    data object Registration : AuthScreen("REGISTRATION")
    data object ForgotPassword : AuthScreen("FORGOT-PASSWORD")
}