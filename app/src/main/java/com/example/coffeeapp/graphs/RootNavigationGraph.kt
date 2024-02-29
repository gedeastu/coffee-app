package com.example.coffeeapp.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.coffeeapp.persentation.sign_in.GoogleAuthUIClient
import com.example.coffeeapp.ui.screens.MainScreen


@Composable
fun RootNavigationGraph(googleAuthUIClient: GoogleAuthUIClient){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Graph.AUTHENTICATION, route = Graph.ROOT){
        authNavGraph(navController = navController, googleAuthUIClient)
        composable(route = Graph.MAIN_SCREEN_PAGE){
            MainScreen()
        }
    }
}
