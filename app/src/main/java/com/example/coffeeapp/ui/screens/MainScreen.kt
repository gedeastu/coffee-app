package com.example.coffeeapp.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.coffeeapp.graphs.MainNavGraph
import com.example.coffeeapp.ui.widgets.BottomBar

@Composable
fun MainScreen(navController: NavHostController = rememberNavController()){
    Scaffold( bottomBar = { BottomBar(navController = navController) } )
    { paddingValues ->
        var modifier =  Modifier.padding(paddingValues)
        MainNavGraph(navController = navController)
    }
}