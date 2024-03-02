package com.example.coffeeapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.coffeeapp.graphs.AuthScreen
import com.example.coffeeapp.graphs.BottomBarNavGraph
import com.example.coffeeapp.graphs.Graph
import com.example.coffeeapp.persentation.sign_in.GoogleAuthUIClient
import com.example.coffeeapp.persentation.sign_in.SignInViewModel
import com.example.coffeeapp.persentation.swipe_refresh.SwipeRefreshViewModel
import com.example.coffeeapp.ui.screens.SplashScreen
import com.example.coffeeapp.ui.screens.authScreens.LoginScreen
import com.example.coffeeapp.ui.screens.contentsScreens.HomeScreen
import com.example.coffeeapp.ui.screens.contentsScreens.ProfileScreen
import com.example.coffeeapp.ui.theme.CoffeeAppTheme
import com.example.coffeeapp.ui.widgets.BottomBar
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.google.android.gms.auth.api.identity.Identity
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val googleAuthUIClient by lazy {
        GoogleAuthUIClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

    //@OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoffeeAppTheme {
                val swipeRefreshViewModel = viewModel<SwipeRefreshViewModel>()
                val isLoading by swipeRefreshViewModel.isLoading.collectAsState()
                val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)

                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                Scaffold(
                    bottomBar = { BottomBar(navController = navController) }
                ){paddingValues ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues = paddingValues),
                    ) {

                        NavHost(
                            navController = navController,
                            startDestination = Graph.AUTHENTICATION,
                            route = Graph.ROOT
                        ) {
                            navigation(
                                route = Graph.AUTHENTICATION,
                                startDestination = AuthScreen.SPLASH.route
                            ) {
                                composable(route = AuthScreen.SPLASH.route) {
                                    SplashScreen(navController = navController)
                                }
                                composable(route = AuthScreen.Login.route) {
                                    val viewModel = viewModel<SignInViewModel>()
                                    val state by viewModel.state.collectAsStateWithLifecycle()

                                    LaunchedEffect(key1 = Unit) {
                                        if (googleAuthUIClient.getSignedInUser() != null) {
                                            navController.navigate(Graph.MAIN_SCREEN_PAGE)
                                        }
                                    }

                                    val launcher = rememberLauncherForActivityResult(
                                        contract = ActivityResultContracts.StartIntentSenderForResult(),
                                        onResult = { result ->
                                            if (result.resultCode == RESULT_OK) {
                                                lifecycleScope.launch {
                                                    val signInResult =
                                                        googleAuthUIClient.signInWithIntent(
                                                            intent = result.data ?: return@launch
                                                        )
                                                    viewModel.onSignInResult(signInResult)
                                                }
                                            }
                                        }
                                    )

                                    LaunchedEffect(key1 = state.isSignSuccessful, block = {
                                        if (state.isSignSuccessful) {
                                            Toast.makeText(
                                                applicationContext,
                                                "Sign in Successful",
                                                Toast.LENGTH_LONG
                                            ).show()

                                            navController.navigate(Graph.MAIN_SCREEN_PAGE){

                                            }

                                            viewModel.resetState()
                                        }
                                    })

                                    LoginScreen(
                                        navController = navController,
                                        state = state,
                                        onSignInClick = {
                                            lifecycleScope.launch {
                                                val signInIntentSender = googleAuthUIClient.signIn()
                                                launcher.launch(
                                                    IntentSenderRequest.Builder(
                                                        signInIntentSender ?: return@launch
                                                    ).build()
                                                )
                                            }
                                        })
                                }
                            }
                            navigation(
                                startDestination = BottomBarNavGraph.Home.route,
                                route = Graph.MAIN_SCREEN_PAGE
                            ) {
                                    composable(route = BottomBarNavGraph.Home.route) {
                                        SwipeRefresh(state = swipeRefreshState, onRefresh = swipeRefreshViewModel::loadStuff,indicator = { state, refreshTrigger ->
                                            SwipeRefreshIndicator(
                                                state = state,
                                                refreshTriggerDistance = refreshTrigger,
                                                backgroundColor = Color.DarkGray,
                                                contentColor = Color.White
                                            )
                                        }){
                                            HomeScreen()
                                        }
                                    }
                                    composable(route = BottomBarNavGraph.Profile.route) {
                                        SwipeRefresh(state = swipeRefreshState, onRefresh = swipeRefreshViewModel::loadStuff, indicator = { state, refreshTrigger ->
                                            SwipeRefreshIndicator(
                                                state = state,
                                                refreshTriggerDistance = refreshTrigger,
                                                backgroundColor = Color.DarkGray,
                                                contentColor = Color.White
                                            )
                                        }) {
                                            ProfileScreen(
                                                userData = googleAuthUIClient.getSignedInUser(),
                                                onSignOut = {
                                                    lifecycleScope.launch {
                                                        googleAuthUIClient.signOut()
                                                        Toast.makeText(
                                                            applicationContext,
                                                            "Signed Out",
                                                            Toast.LENGTH_LONG
                                                        ).show()
                                                        navController.navigate(AuthScreen.Login.route)
                                                    }
                                                })
                                        }
                                    }
                                }
                        }
                    }
                }
            }

        }
    }
}