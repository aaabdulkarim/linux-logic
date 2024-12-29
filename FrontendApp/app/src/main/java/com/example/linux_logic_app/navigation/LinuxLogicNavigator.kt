package com.example.linux_logic_app.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.linux_logic_app.screens.*

@Composable
fun LinuxLogicNavigator() {
    /* NavHost, NavController, composables, route, ...
    Accompanist Navigation Animation ist deprecated und man soll daher Compose Animation verwenden,
    um Animationen zwischen Screens umzusetzen.
     */
    val navController = rememberNavController()

    // NavHost mit Animationen für die Navigation
    NavHost(navController = navController, startDestination = Screen.Start.route) {
        // Start Screen
        composable(
            route = Screen.Start.route,
            enterTransition = { fadeIn() + slideInHorizontally(initialOffsetX = { -1000 }, animationSpec = tween(500)) },
            exitTransition = { fadeOut() + slideOutHorizontally(targetOffsetX = { 1000 }, animationSpec = tween(500)) }
        ) {
            StartScreen(navController)
        }

        // Login Screen
        composable(
            route = Screen.Login.route,
            enterTransition = { fadeIn() + slideInHorizontally(initialOffsetX = { 1000 }, animationSpec = tween(500)) },
            exitTransition = { fadeOut() + slideOutHorizontally(targetOffsetX = { -1000 }, animationSpec = tween(500)) }
        ) {
            LoginScreen(navController)
        }

        // Register Screen
        composable(
            route = Screen.Register.route,
            enterTransition = { fadeIn() + slideInVertically(initialOffsetY = { 1000 }, animationSpec = tween(500)) },
            exitTransition = { fadeOut() + slideOutVertically(targetOffsetY = { -1000 }, animationSpec = tween(500)) }
        ) {
            RegisterScreen(navController)
        }

        /*
        z.B.:
        composable(
            route = "${Screen.Login.route}/{userId}",
            arguments = listOf(navArgument("userId") { type = NavType.StringType })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")
            LoginScreen(userId)
            }
         */
    }
}