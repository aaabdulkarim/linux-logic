package com.example.linux_logic_app.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
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

    NavHost(
        navController = navController,
        startDestination = Screen.Start.route
    ) {
        composable(
            route = Screen.Start.route,
            enterTransition = { // Eintritts-Transition
                scaleIn( // Reinzoomen in den Screen (Von einer kleinen Skalierung in eine Größere
                    initialScale = 0.9f, // Starten mit 80% der finalen Größe
                    animationSpec = tween(600) // Die Dauer der Animation in Millisekunden
                ) + fadeIn() // Eine weiche Übergangsanimation mittels alpha Value, langsam sichtbar
            },
            exitTransition = { // Austritts-Transition
                scaleOut( // Rauszoomen
                    targetScale = 0.9f,
                    animationSpec = tween(600)
                ) + fadeOut() // Weiche Übergangsanimation vom sichtbar zu langsam unsichtbar
            }
        ) {
            StartScreen(navController)
        }

        composable(
            route = Screen.Login.route,
            enterTransition = {
                scaleIn(
                    initialScale = 0.9f,
                    animationSpec = tween(600)
                ) + fadeIn()
            },
            exitTransition = {
                scaleOut(
                    targetScale = 0.9f,
                    animationSpec = tween(600)
                ) + fadeOut()
            }
        ) {
            LoginScreen(navController)
        }

        composable(
            route = Screen.Register.route,
            enterTransition = {
                scaleIn(
                    initialScale = 0.9f,
                    animationSpec = tween(600)
                ) + fadeIn()
            },
            exitTransition = {
                scaleOut(
                    targetScale = 0.9f,
                    animationSpec = tween(600)
                ) + fadeOut()
            }
        ) {
            RegisterScreen(navController)
        }

        composable(
            route = Screen.Main.route,
            enterTransition = {
                scaleIn(
                    initialScale = 0.9f,
                    animationSpec = tween(600)
                ) + fadeIn()
            },
            exitTransition = {
                scaleOut(
                    targetScale = 0.9f,
                    animationSpec = tween(600)
                ) + fadeOut()
            }
        ) {
            MainScreen(navController)
        }

        composable(
            route = Screen.New.route,
            enterTransition = {
                scaleIn(
                    initialScale = 0.9f,
                    animationSpec = tween(600)
                ) + fadeIn()
            },
            exitTransition = {
                scaleOut(
                    targetScale = 0.9f,
                    animationSpec = tween(600)
                ) + fadeOut()
            }
        ) {
            NewScreen(navController)
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