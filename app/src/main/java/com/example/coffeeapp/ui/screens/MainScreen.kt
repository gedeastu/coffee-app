package com.example.coffeeapp.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.coffeeapp.ui.widgets.BottomBar

@Composable
fun MainScreen(navController: NavHostController, content: @Composable (Modifier) -> Unit){
    Scaffold( bottomBar = { BottomBar(navController = navController) } )
    { paddingValues ->
       content(Modifier.padding(paddingValues))
    }
}