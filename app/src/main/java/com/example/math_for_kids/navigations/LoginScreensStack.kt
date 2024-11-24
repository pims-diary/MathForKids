package com.example.math_for_kids.navigations

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.math_for_kids.view.screens.AppLandingScreen
import com.example.math_for_kids.view.screens.HomeScreen
import com.example.math_for_kids.view.screens.authentication.LoginScreen
import com.example.math_for_kids.view.screens.authentication.RegistrationScreen
import com.example.math_for_kids.viewmodel.AuthenticationViewModel

sealed class AuthPages(val route: String) {
    data object Login : AuthPages("login")
    data object Registration : AuthPages("registration")
    data object AppLanding : AuthPages("landing")
    data object Home : AuthPages("home")
}

@Composable
fun LoginScreensStack() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AuthPages.Login.route) {
        composable(AuthPages.Login.route) { LoginScreen(navController) }
        composable(AuthPages.Registration.route) { RegistrationScreen(navController) }
        composable(AuthPages.AppLanding.route) { AppLandingScreen(navController) }
        composable(AuthPages.Home.route) { HomeScreen(navController) }
    }
}