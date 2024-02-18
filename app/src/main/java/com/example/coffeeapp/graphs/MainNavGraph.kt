package com.example.coffeeapp.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.coffeeapp.ui.screens.contentsScreens.HomeScreen

@Composable
fun MainNavGraph(navController: NavHostController){
    NavHost(navController = navController, startDestination = BottomBarNavGraph.Home.route, route = Graph.MAIN_SCREEN_PAGE){
        homeNavGraph(navController = navController)
    }
}

fun NavGraphBuilder.homeNavGraph(navController: NavController){
    composable(route = BottomBarNavGraph.Home.route){
        HomeScreen()
    }
}