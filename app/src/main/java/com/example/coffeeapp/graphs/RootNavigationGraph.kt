package com.example.coffeeapp.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.coffeeapp.ui.screens.MainScreen

@Composable
fun RootNavigationGraph(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Graph.AUTHENTICATION, route = Graph.ROOT){
        authNavGraph(navController = navController)
        composable(route = Graph.MAIN_SCREEN_PAGE){
            MainScreen()
        }
    }
}
