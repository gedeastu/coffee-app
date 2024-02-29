package com.example.coffeeapp.graphs

import android.app.Instrumentation.ActivityResult
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.coffeeapp.persentation.sign_in.GoogleAuthUIClient
import com.example.coffeeapp.persentation.sign_in.SignInViewModel
import com.example.coffeeapp.ui.screens.SplashScreen
import com.example.coffeeapp.ui.screens.authScreens.LoginScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController,googleAuthUIClient: GoogleAuthUIClient, onResult: (ActivityResult) -> Unit = {}){
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.SPLASH.route
    ){
        composable(route = AuthScreen.SPLASH.route){
           SplashScreen( navController = navController )
        }
        composable(route = AuthScreen.Login.route){
            val viewModel = viewModel<SignInViewModel>()
            val state by viewModel.state.collectAsStateWithLifecycle()

            LaunchedEffect(key1 = Unit, block = {
                if (googleAuthUIClient.getSignedInUser() != null){
                    navController.navigate("HOME")
                }
            })
            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartIntentSenderForResult(),
                onResult = { result ->
                    if ( result.resultCode == RESULT_OK ){
                        lifecycleScope.launch{
                            val signInResult = googleAuthUIClient.signInWithIntent(
                                intent = result.data ?: result@launch
                            )
                            viewModel.onSignInResult(signInResult)
                        }
                    }
                }
            )

            LaunchedEffect(key1 = state.isSignSuccessful, block = {
                if (state.isSignSuccessful){
                    Toast.makeText()
                }
            })

            LoginScreen( navController = navController, state = state, onSignInClick = {

            })
        }
    }
}
sealed class AuthScreen(val route : String,){
    data object SPLASH : AuthScreen("SPLASH")
    data object Login : AuthScreen("LOGIN")
    data object Registration : AuthScreen("REGISTRATION")
    data object ForgotPassword : AuthScreen("FORGOT-PASSWORD")
}