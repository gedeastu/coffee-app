package com.example.coffeeapp.graphs

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarNavGraph (
    val route: String,
    val title: String,
    val icon: ImageVector
    ){
    data object Home: BottomBarNavGraph(
        route = "HOME",
        title = "Home",
        icon = Icons.Default.Home
    )
    data object Keranjang: BottomBarNavGraph(
        route = "KERANJANG",
        title = "Keranjang",
        icon = Icons.Default.ShoppingCart
    )

    data object Riwayat: BottomBarNavGraph(
        route = "RIWAYAT",
        title = "Riwayat",
        icon = Icons.Default.List
    )
    data object Profile: BottomBarNavGraph(
        route = "PROFILE",
        title = "Profile",
        icon = Icons.Default.AccountCircle
    )
}