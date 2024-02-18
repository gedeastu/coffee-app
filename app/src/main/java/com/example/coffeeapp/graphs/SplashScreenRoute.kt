package com.example.coffeeapp.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.coffeeapp.ui.screens.SplashScreen

fun NavGraphBuilder.splashNavGraph(navController: NavController){
    composable(
        route = AuthScreen.SPLASH.route
    ){
        SplashScreen(navController = navController)
    }
}