package com.example.linux_logic_app.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.linux_logic_app.components.viewmodels.UserViewModel
import com.example.linux_logic_app.screens.FeedbackScreen
import com.example.linux_logic_app.screens.HelpScreen
import com.example.linux_logic_app.screens.gamification.LevelScreen
import com.example.linux_logic_app.screens.authentication.LoginScreen
import com.example.linux_logic_app.screens.MainScreen
import com.example.linux_logic_app.screens.NotificationScreen
import com.example.linux_logic_app.screens.authentication.RegisterScreen
import com.example.linux_logic_app.screens.authentication.SettingsScreen
import com.example.linux_logic_app.screens.StartScreen

/**
 * Der LinuxLogicNavigator ist das zentrale Navigationselement der App.
 * Dieses Composable definiert die Navigation innerhalb der Linux Logic App mittels eines NavHosts.
 * Es legt den Startscreen sowie Ein- und Austrittsanimationen für den Übergang zwischen den Screens fest.
 * Relevante und komplexe Aspekte:
 * - Verwendung von NavHost und rememberNavController zur Verwaltung der Navigation.
 * - Definierte Animationstransitionen (enter, exit, popEnter, popExit) für flüssige Bildschirmwechsel.
 * - Verknüpfung der einzelnen Screens (Start, Login, Register, etc.) mit ihren jeweiligen Routen.
 *
 * @param userViewModel ViewModel, das den Benutzerzustand und weitere Logik verwaltet.
 */
@Composable
fun LinuxLogicNavigator(userViewModel: UserViewModel) {
    // Erzeugt und merkt einen NavController, der zur Navigation zwischen Screens dient.
    val navController = rememberNavController()

    // NavHost definiert den Navigationsrahmen und die Startdestination, sowie Übergänge
    NavHost(
        navController = navController,
        startDestination = Screen.Start.route,
        // Animationsübergänge beim Betreten eines neuen Screens.
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Start,
                tween(500) // Animation dauert 500 ms
            )
        },
        // Animationsübergänge beim Verlassen eines Screens.
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Start,
                tween(500)
            )
        },
        // Übergangsanimation beim Zurücknavigieren in den vorherigen Screen.
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.End,
                tween(500)
            )
        },
        // Übergangsanimation beim Verlassen des aktuellen Screens beim Zurücknavigieren.
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.End,
                tween(500)
            )
        }
    ) {
        // Definiert die Route und das zugehörige Composable für den Start-Screen.
        composable(route = Screen.Start.route) {
            StartScreen(navController = navController)
        }

        // Definiert die Route und das zugehörige Composable für den Login-Screen.
        composable(route = Screen.Login.route) {
            LoginScreen(navController = navController, userViewModel = userViewModel)
        }

        // Definiert die Route und das zugehörige Composable für den Registrierungs-Screen.
        composable(route = Screen.Register.route) {
            RegisterScreen(navController = navController, userViewModel = userViewModel)
        }

        // Definiert die Route und das zugehörige Composable für den Main-Screen.
        composable(route = Screen.Main.route) {
            MainScreen(navController = navController, userViewModel = userViewModel)
        }

        // Definiert die Route und das zugehörige Composable für den Level-Screen.
        composable(route = Screen.Level.route) {
            LevelScreen(navController = navController, userViewModel = userViewModel)
        }

        // Definiert die Route und das zugehörige Composable für den Settings-Screen.
        composable(route = Screen.Settings.route) {
            SettingsScreen(navController = navController, userViewModel = userViewModel)
        }

        // Definiert die Route und das zugehörige Composable für den Notifications-Screen.
        composable(route = Screen.Notifications.route) {
            NotificationScreen(navController = navController)
        }

        // Definiert die Route und das zugehörige Composable für den Feedback-Screen.
        composable(route = Screen.Feedback.route) {
            FeedbackScreen(navController = navController)
        }

        // Definiert die Route und das zugehörige Composable für den Help-Screen.
        composable(route = Screen.Help.route) {
            HelpScreen(navController = navController)
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
