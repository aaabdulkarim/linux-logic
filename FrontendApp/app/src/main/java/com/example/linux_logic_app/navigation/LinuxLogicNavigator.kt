package com.example.linux_logic_app.navigation

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.linux_logic_app.screens.LoginScreen
import com.example.linux_logic_app.screens.MainScreen
import com.example.linux_logic_app.screens.ProfileScreen
import com.example.linux_logic_app.screens.RegisterScreen
import com.example.linux_logic_app.screens.StartScreen

@Composable
fun LinuxLogicNavigator() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Start.route
    ) {
        composable(
            route = Screen.Start.route,
            enterTransition = {
                // Eingangsanimation: Sanftes Reinzoomen
                fadeIn(animationSpec = tween(800, easing = LinearOutSlowInEasing)) +
                        scaleIn(
                            initialScale = 0.9f,
                            animationSpec = tween(800, easing = LinearOutSlowInEasing)
                        )
            },
            exitTransition = {
                // Ausgangsanimation: Sanftes Verkleinern und Ausblenden
                fadeOut(animationSpec = tween(800, easing = LinearOutSlowInEasing)) +
                        scaleOut(
                            targetScale = 0.9f,
                            animationSpec = tween(800, easing = LinearOutSlowInEasing)
                        )
            }
        ) {
            StartScreen(navController)
        }

        composable(
            route = Screen.Login.route,
            enterTransition = {
                // Eingangsanimation: Sanftes Reinzoomen
                fadeIn(animationSpec = tween(800, easing = LinearOutSlowInEasing)) +
                        scaleIn(
                            initialScale = 0.9f,
                            animationSpec = tween(800, easing = LinearOutSlowInEasing)
                        )
            },
            exitTransition = {
                // Ausgangsanimation: Sanftes Verkleinern und Ausblenden
                fadeOut(animationSpec = tween(800, easing = LinearOutSlowInEasing)) +
                        scaleOut(
                            targetScale = 0.9f,
                            animationSpec = tween(800, easing = LinearOutSlowInEasing)
                        )
            }
        ) {
            LoginScreen(navController)
        }

        composable(
            route = Screen.Register.route,
            enterTransition = {
                // Eingangsanimation: Sanftes Reinzoomen
                fadeIn(animationSpec = tween(800, easing = LinearOutSlowInEasing)) +
                        scaleIn(
                            initialScale = 0.9f,
                            animationSpec = tween(800, easing = LinearOutSlowInEasing)
                        )
            },
            exitTransition = {
                // Ausgangsanimation: Sanftes Verkleinern und Ausblenden
                fadeOut(animationSpec = tween(800, easing = LinearOutSlowInEasing)) +
                        scaleOut(
                            targetScale = 0.9f,
                            animationSpec = tween(800, easing = LinearOutSlowInEasing)
                        )
            }
        ) {
            RegisterScreen(navController)
        }

        composable(
            route = Screen.Main.route,
            enterTransition = {
                // Eingangsanimation: Sanftes Reinzoomen
                fadeIn(animationSpec = tween(800, easing = LinearOutSlowInEasing)) +
                        scaleIn(
                            initialScale = 0.9f,
                            animationSpec = tween(800, easing = LinearOutSlowInEasing)
                        )
            },
            exitTransition = {
                // Ausgangsanimation: Sanftes Verkleinern und Ausblenden
                fadeOut(animationSpec = tween(800, easing = LinearOutSlowInEasing)) +
                        scaleOut(
                            targetScale = 0.9f,
                            animationSpec = tween(800, easing = LinearOutSlowInEasing)
                        )
            }
        ) {
            MainScreen(navController)
        }

        composable(
            route = Screen.Settings.route,
            enterTransition = {
                // Eingangsanimation: Sanftes Reinzoomen
                fadeIn(animationSpec = tween(800, easing = LinearOutSlowInEasing)) +
                        scaleIn(
                            initialScale = 0.9f,
                            animationSpec = tween(800, easing = LinearOutSlowInEasing)
                        )
            },
            exitTransition = {
                // Ausgangsanimation: Sanftes Verkleinern und Ausblenden
                fadeOut(animationSpec = tween(800, easing = LinearOutSlowInEasing)) +
                        scaleOut(
                            targetScale = 0.9f,
                            animationSpec = tween(800, easing = LinearOutSlowInEasing)
                        )
            }
        ) {
            ProfileScreen(navController)
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
