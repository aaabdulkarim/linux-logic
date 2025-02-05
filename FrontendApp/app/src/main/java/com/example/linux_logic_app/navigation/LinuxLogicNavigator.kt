package com.example.linux_logic_app.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.linux_logic_app.screens.LoginScreen
import com.example.linux_logic_app.screens.MainScreen
import com.example.linux_logic_app.screens.RegisterScreen
import com.example.linux_logic_app.screens.SettingsScreen
import com.example.linux_logic_app.screens.StartScreen

@Composable
fun LinuxLogicNavigator() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Start.route,
        enterTransition = { slideIntoContainer(
            AnimatedContentTransitionScope.SlideDirection.Start, tween(
                500
            )
        ) },
        exitTransition = { slideOutOfContainer(
            AnimatedContentTransitionScope.SlideDirection.Start, tween(
                500
            )
        ) },
        popEnterTransition = { slideIntoContainer(
            AnimatedContentTransitionScope.SlideDirection.End, tween(
                500
            )
        ) },
        popExitTransition = { slideOutOfContainer(
            AnimatedContentTransitionScope.SlideDirection.End, tween(
                500
            )
        ) }
    ) {
        composable(
            route = Screen.Start.route,
        ) {
            StartScreen(navController)
        }

        composable(
            route = Screen.Login.route,
        ) {
            LoginScreen(navController)
        }

        composable(
            route = Screen.Register.route,
        ) {
            RegisterScreen(navController)
        }

        composable(
            route = Screen.Main.route,
        ) {
            MainScreen(navController)
        }

        composable(
            route = Screen.Settings.route,
        ) {
            SettingsScreen(navController)
        }
    }
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
