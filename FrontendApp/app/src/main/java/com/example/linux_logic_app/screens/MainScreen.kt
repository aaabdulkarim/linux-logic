package com.example.linux_logic_app.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Shortcut
import androidx.compose.material.icons.automirrored.twotone.Help
import androidx.compose.material.icons.automirrored.twotone.Logout
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material.icons.twotone.AccountCircle
import androidx.compose.material.icons.twotone.Feedback
import androidx.compose.material.icons.twotone.Home
import androidx.compose.material.icons.twotone.Info
import androidx.compose.material.icons.twotone.Menu
import androidx.compose.material.icons.twotone.NotificationsActive
import androidx.compose.material.icons.twotone.Search
import androidx.compose.material.icons.twotone.Security
import androidx.compose.material.icons.twotone.Settings
import androidx.compose.material.icons.twotone.SportsEsports
import androidx.compose.material.icons.twotone.Terminal
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.linux_logic_app.R
import com.example.linux_logic_app.components.UserViewModel
import com.example.linux_logic_app.navigation.Screen
import com.example.linux_logic_app.screens.gamification.CustomizationScreen
import com.example.linux_logic_app.screens.gamification.PlayScreen
import com.example.linux_logic_app.ui.theme.LiloBlue
import com.example.linux_logic_app.ui.theme.LiloMain
import com.example.linux_logic_app.ui.theme.LiloOrange
import kotlinx.coroutines.launch

/*
https://stackoverflow.com/questions/67025228/how-to-create-a-second-drawer-in-jetpack-compose
Probleme beim implementieren eines rechtbündigen Navigation Drawers
 */

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController, userViewModel: UserViewModel) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val endDrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(1) }
    val navControllerMain = rememberNavController()
    val context = LocalContext.current
    val liloWebUrl = "https://www.linux-logic.com"
    val liloIntent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(liloWebUrl)) }
    val items = listOf(
        BottomNavigationItem(
            title = "Terminal",
            selectedIcon = Icons.Filled.Terminal,
            unselectedIcon = Icons.TwoTone.Terminal,
            hasNews = true,
            badgeCount = 1,
        ),
        BottomNavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.TwoTone.Home,
            hasNews = false,
        ),
        BottomNavigationItem(
            title = "Spielen",
            selectedIcon = Icons.Filled.SportsEsports,
            unselectedIcon = Icons.TwoTone.SportsEsports,
            hasNews = false,
        )
    )

    //val terminalViewModel: TerminalViewModel = viewModel()
    //val settingsViewModel: SettingsViewModel = viewModel()

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = MaterialTheme.colorScheme.background,
                drawerContentColor = MaterialTheme.colorScheme.onBackground,
                drawerShape = RoundedCornerShape(
                    topEnd = 64.dp,
                    bottomEnd = 64.dp
                ), // Abgerundete Ecken am Ende
                drawerState = endDrawerState, // Der EndDrawerState (Verwendung von `rememberDrawerState()` zur Initialisierung)
            ) {
                Row(
                    modifier = Modifier
                        .padding(start = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.Shortcut,
                        contentDescription = "Shortcut Icon for Main",
                        tint = LiloMain
                    )

                    Text(
                        text = "Shortcuts",
                        modifier = Modifier
                            .padding(16.dp),
                        style = MaterialTheme.typography.labelLarge,
                    )
                }

                HorizontalDivider(
                    thickness = 5.dp,
                    color = LiloOrange
                )

                NavigationDrawerItem(
                    label = {
                        Text(
                            text = "Account Informationen",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    },
                    selected = false,
                    icon = {
                        Icon(
                            Icons.TwoTone.Security,
                            contentDescription = "Security icon for Main",
                            tint = LiloMain
                        )
                    },
                    onClick = {
                        //settingsViewModel.updateAccountCardExpanded(expanded = true)
                        navController.navigate(Screen.Settings.route)
                    }
                )

                HorizontalDivider(
                    thickness = 1.dp,
                    color = LiloBlue
                )

                NavigationDrawerItem(
                    label = {
                        Text(
                            text = "Suchen",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    },
                    selected = false,
                    icon = {
                        Icon(
                            Icons.TwoTone.Search,
                            contentDescription = "Search Icon for Main",
                            tint = LiloMain
                        )
                    },
                    onClick = {

                    }
                )

                HorizontalDivider(
                    thickness = 1.dp,
                    color = LiloBlue
                )

                NavigationDrawerItem(
                    label = {
                        Text(
                            text = "Mehr über Linux Logic",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    },
                    selected = false,
                    icon = {
                        Icon(
                            Icons.TwoTone.Info,
                            contentDescription = "Info Icon for Main",
                            tint = LiloMain
                        )
                    },
                    onClick = {
                        context.startActivity(liloIntent)
                    }
                )
            }
        }
    ) {
        ModalNavigationDrawer(
            drawerState = endDrawerState,
            gesturesEnabled = endDrawerState.isOpen,
            drawerContent = {
                ModalDrawerSheet(
                    drawerContainerColor = MaterialTheme.colorScheme.background,
                    drawerContentColor = MaterialTheme.colorScheme.onBackground,
                    drawerShape = RoundedCornerShape(
                        topEnd = 64.dp,
                        bottomEnd = 64.dp
                    ), // Abgerundete Ecken am Ende
                    drawerState = endDrawerState, // Der EndDrawerState (Verwendung von `rememberDrawerState()` zur Initialisierung)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(start = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.TwoTone.AccountCircle,
                            contentDescription = "ManageAccounts Icon for Main",
                            tint = LiloMain
                        )
                        Text(
                            text = "Hallo ${userViewModel.user?.username}",
                            modifier = Modifier
                                .padding(vertical = 16.dp, horizontal = 8.dp),
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }


                    HorizontalDivider(
                        thickness = 5.dp,
                        color = LiloOrange
                    )

                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = "Einstellungen",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        },
                        selected = false,
                        icon = {
                            Icon(
                                Icons.TwoTone.Settings,
                                contentDescription = "Settings Icon for Main",
                                tint = LiloMain
                            )
                        },
                        onClick = {
                            navController.navigate(Screen.Settings.route)
                        }
                    )

                    HorizontalDivider(
                        thickness = 1.dp,
                        color = LiloBlue
                    )

                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = "Mitteilungen",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        },
                        selected = false,
                        icon = {
                            Icon(
                                Icons.TwoTone.NotificationsActive,
                                contentDescription = "NotificationsActive Icon for Main",
                                tint = LiloMain
                            )
                        },
                        onClick = {
                            navController.navigate(Screen.Notifications.route)
                        }
                    )

                    HorizontalDivider(
                        thickness = 1.dp,
                        color = LiloBlue
                    )

                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = "Feedback senden",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        },
                        selected = false,
                        icon = {
                            Icon(
                                Icons.TwoTone.Feedback,
                                contentDescription = "RateReview Icon for Main",
                                tint = LiloMain
                            )
                        },
                        onClick = {
                            navController.navigate(Screen.Feedback.route)
                        }
                    )

                    HorizontalDivider(
                        thickness = 1.dp,
                        color = LiloBlue
                    )

                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = "Hilfe",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        },
                        selected = false,
                        icon = {
                            Icon(
                                Icons.AutoMirrored.TwoTone.Help,
                                contentDescription = "Help Icon for Main",
                                tint = LiloMain
                            )
                        },
                        onClick = {
                            navController.navigate(Screen.Help.route)
                        }
                    )

                    HorizontalDivider(
                        thickness = 1.dp,
                        color = LiloBlue
                    )

                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = "Abmelden",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        },
                        selected = false,
                        icon = {
                            Icon(
                                Icons.AutoMirrored.TwoTone.Logout,
                                contentDescription = "Logout Icon for Main",
                                tint = LiloMain
                            )
                        },
                        onClick = {
                            userViewModel.logout()
                            navController.navigate(Screen.Start.route) {
                                // Löscht den gesamten Navigationsverlauf, damit der Nutzer nicht zurückkehren kann
                                popUpTo(0) { inclusive = true }
                                // Stellt sicher, dass keine doppelte Instanz des Startscreens erstellt wird
                                launchSingleTop = true
                            }
                        }
                    )
                }
            },
        ) {
            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        title = {
                            Row(
                                verticalAlignment = Alignment.CenterVertically, // Bilder vertikal zentrieren
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.linux_logic_pinguin),
                                    contentDescription = "Linux Logic Pinguin",
                                )

                                Text(
                                    text = "Linux Logic",
                                    style = MaterialTheme.typography.labelMedium
                                )
                                /*
                                Image(
                                    painter = painterResource(id = R.drawable.linux_logic_main_transparent_2),
                                    contentDescription = "Linux Logic Logo",
                                    modifier = Modifier
                                        .size(128.dp) // Passe die Größe an
                                )
                                */
                            }
                        },
                        navigationIcon = {
                            IconButton(
                                onClick = {
                                    coroutineScope.launch {
                                        drawerState.open()
                                    }
                                }
                            ) {
                                Icon(
                                    Icons.TwoTone.Menu,
                                    contentDescription = "Hamburger Menu for Main",
                                    tint = Color.White
                                )
                            }
                        },
                        actions = {
                            IconButton(
                                onClick = {
                                    coroutineScope.launch {
                                        endDrawerState.open()
                                    }
                                }
                            ) {
                                Icon(
                                    Icons.TwoTone.AccountCircle,
                                    contentDescription = "AccountCircle Icon for Main",
                                    tint = Color.White // Die Farbe wird hier nicht automatisch auf Weiß gesetzt?
                                )
                            }
                        },
                        colors = TopAppBarColors(
                            containerColor = LiloMain,
                            scrolledContainerColor = LiloMain,
                            navigationIconContentColor = Color.White, // Hier setze ich die Farbe Weiß für die Icons ?
                            titleContentColor = Color.White,
                            actionIconContentColor = LiloMain
                        )
                    )
                },
                /*floatingActionButton = {
                    FloatingActionButton(
                        onClick = {

                        },
                        containerColor = Color(0xFFFF8c00),
                        contentColor = Color.White
                    ) {
                        Icon(
                            Icons.TwoTone.Place,
                            contentDescription = "Add Icon for Main"
                        )
                    }
                },*/
                bottomBar = {
                    NavigationBar(
                        containerColor = Color(0xFF445a65),
                        contentColor = Color.White
                    ) {
                        items.forEachIndexed { index, item ->
                            NavigationBarItem(
                                colors = NavigationBarItemDefaults.colors(
                                    unselectedIconColor = Color.White,
                                    selectedIconColor = Color.White,
                                    selectedTextColor = Color.White,
                                    indicatorColor = Color(0xFFFF8c00)
                                ),
                                label = {
                                    Text(
                                        text = item.title,
                                        style = MaterialTheme.typography.labelSmall,
                                    )
                                },
                                alwaysShowLabel = false,
                                selected = selectedItemIndex == index,
                                onClick = {
                                    selectedItemIndex = index
                                    navControllerMain.navigate(item.title) {
                                        popUpTo(navControllerMain.graph.startDestinationId) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                },
                                icon = {
                                    BadgedBox(
                                        badge = {
                                            if (item.badgeCount != null) {
                                                Badge(
                                                    containerColor = Color(0xFFA00000),
                                                ) {
                                                    Text(
                                                        text = "!",//item.badgeCount.toString(),
                                                        style = MaterialTheme.typography.labelSmall
                                                    )
                                                }
                                            } else if (item.hasNews) {
                                                Badge()
                                            }
                                        }
                                    ) {
                                        Icon(
                                            imageVector = if (index == selectedItemIndex) {
                                                item.selectedIcon
                                            } else {
                                                item.unselectedIcon
                                            },
                                            contentDescription = item.title + "for Main"
                                        )
                                    }
                                }
                            )
                        }
                    }
                    /*BottomAppBar(
                        containerColor = Color(0xFF445a65),
                        contentColor = Color.White
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            IconButton(
                                onClick = {

                                },
                                modifier = Modifier,

                            ) {
                                Icon(
                                    Icons.TwoTone.Build,
                                    contentDescription = "Neues Icon for Main"
                                )
                            }
                            Text(
                                text = "Neues",
                                style =  MaterialTheme.typography.bodyLarge
                            )
                        }

                        Column(
                            modifier = Modifier
                                .weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            IconButton(
                                onClick = {

                                }
                            ) {
                                Icon(
                                    Icons.TwoTone.Home,
                                    contentDescription = "Home Icon for Main"
                                )
                            }
                            Text(
                                text = "Home",
                                style =  MaterialTheme.typography.bodyLarge
                            )
                        }

                        Column(
                            modifier = Modifier
                                .weight(1f),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            IconButton(
                                onClick = {

                                }
                            ) {
                                Icon(Icons.TwoTone.PlayArrow,
                                    contentDescription = "Lernen Icon for Main"
                                )
                            }
                            Text(
                                text= "Spielen",
                                style =  MaterialTheme.typography.bodyLarge
                            )
                        }
                    }*/
                }
            ) { innerPadding ->
                Box(
                    modifier = Modifier
                        .padding(innerPadding)
                ) {
                    NavHost(
                        navController = navControllerMain,
                        startDestination = "Home",
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        composable(
                            route = Screen.Customize.route
                        ) {
                            CustomizationScreen(userViewModel = userViewModel)
                        }

                        composable(
                            route = Screen.Home.route
                        ) {
                            HomeScreen(userViewModel = userViewModel)
                        }

                        composable(
                            route = Screen.Play.route
                        ) {
                            PlayScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}