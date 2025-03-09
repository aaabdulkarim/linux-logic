package com.example.linux_logic_app.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.linux_logic_app.components.UserViewModel
import com.example.linux_logic_app.screens.FeedbackScreen
import com.example.linux_logic_app.screens.HelpScreen
import com.example.linux_logic_app.screens.LevelScreen
import com.example.linux_logic_app.screens.LoginScreen
import com.example.linux_logic_app.screens.MainScreen
import com.example.linux_logic_app.screens.NotificationScreen
import com.example.linux_logic_app.screens.RegisterScreen
import com.example.linux_logic_app.screens.SettingsScreen
import com.example.linux_logic_app.screens.StartScreen

@Composable
fun LinuxLogicNavigator(userViewModel: UserViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Start.route,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Start,
                tween(
                    500
                )
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Start,
                tween(
                    500
                )
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.End,
                tween(
                    500
                )
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.End,
                tween(
                    500
                )
            )
        }
    ) {
        composable(
            route = Screen.Start.route,
        ) {
            StartScreen(navController = navController)
        }

        composable(
            route = Screen.Login.route,
        ) {
            LoginScreen(navController = navController, userViewModel = userViewModel)
        }

        composable(
            route = Screen.Register.route,
        ) {
            RegisterScreen(navController = navController, userViewModel = userViewModel)
        }

        composable(
            route = Screen.Main.route,
        ) {
            MainScreen(navController = navController, userViewModel = userViewModel)
        }

        composable(
            route = Screen.Level.route
        ) {
            LevelScreen(navController = navController, userViewModel = userViewModel)
        }

        composable(
            route = Screen.Settings.route,
        ) {
            SettingsScreen(navController = navController, userViewModel = userViewModel)
        }

        /*composable(route = Screen.Settings.route) { backStackEntry ->
            // Hol dir das ViewModel aus dem gleichen Store
            val settingsViewModel: SettingsViewModel = viewModel(backStackEntry)
            SettingsScreen(
                navController = navController,
                userViewModel = userViewModel,
                settingsViewModel = settingsViewModel
            )
        }*/

        composable(
            route = Screen.Notifications.route,
        ) {
            NotificationScreen(navController = navController)
        }

        composable(
            route = Screen.Feedback.route,
        ) {
            FeedbackScreen(navController = navController)
            //ComponentsScreen()
        }

        composable(
            route = Screen.Help.route,
        ) {
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
