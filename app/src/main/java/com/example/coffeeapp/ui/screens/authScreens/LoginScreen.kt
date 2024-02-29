package com.example.coffeeapp.ui.screens.authScreens

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.coffeeapp.graphs.AuthScreen
import com.example.coffeeapp.graphs.Graph
import com.example.coffeeapp.persentation.sign_in.SignInState

@Composable
fun LoginScreen(state: SignInState, onSignInClick:()->Unit, navController: NavController) {

    val context = LocalContext.current

    LaunchedEffect(key1 = state.signInError, block = {
        state.signInError?.let{ error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    })

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Column(modifier = Modifier){
            Text(text = "LOGIN")
            Button(onClick = {
                onSignInClick
                navController.navigate(Graph.MAIN_SCREEN_PAGE) {
                    popUpTo(AuthScreen.Login.route) { inclusive = true }
                }
            }) {
                Text(text = "Login with Google")
            }
        }
    }
}