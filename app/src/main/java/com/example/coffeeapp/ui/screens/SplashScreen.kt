package com.example.coffeeapp.ui.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.coffeeapp.R
import com.example.coffeeapp.graphs.AuthScreen
import com.example.coffeeapp.graphs.Graph

@Composable
fun SplashScreen(navController: NavController){
    val scale = remember {
        androidx.compose.animation.core.Animatable(
            0f
        )
    }
    LaunchedEffect(key1 = Unit, block = {
        scale.animateTo(
            targetValue = 0.3f,
            animationSpec = tween(
                durationMillis = 500,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        navController.navigate(Graph.MAIN_SCREEN_PAGE){
            popUpTo(AuthScreen.SPLASH.route){
                inclusive = true
            }
        }
    })
    Box(modifier = Modifier.fillMaxSize().background(color = Color.DarkGray), contentAlignment = Alignment.Center){
        Image(modifier = Modifier.scale(scale.value),painter = painterResource(id = R.drawable.logocoffee), contentDescription = null)
    }
}