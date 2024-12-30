package com.example.linux_logic_app.navigation

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
    NavHost(navController = navController, startDestination = Screen.Start.route) {
        composable(
            route = Screen.Start.route
        ) {
            StartScreen(navController)
        }

        composable(
            route = Screen.Login.route
        ) {
            LoginScreen(navController)
        }

        composable(
            route = Screen.Register.route
        ) {
            RegisterScreen(navController)
        }

        composable(
            route = Screen.Main.route
        ) {
            MainScreen(navController)
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