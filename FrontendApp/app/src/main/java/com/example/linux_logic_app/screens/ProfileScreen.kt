package com.example.linux_logic_app.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Shortcut
import androidx.compose.material.icons.automirrored.twotone.Help
import androidx.compose.material.icons.automirrored.twotone.Logout
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material.icons.twotone.AccountCircle
import androidx.compose.material.icons.twotone.Home
import androidx.compose.material.icons.twotone.Info
import androidx.compose.material.icons.twotone.ManageAccounts
import androidx.compose.material.icons.twotone.Menu
import androidx.compose.material.icons.twotone.NotificationsActive
import androidx.compose.material.icons.twotone.PlayArrow
import androidx.compose.material.icons.twotone.RateReview
import androidx.compose.material.icons.twotone.Search
import androidx.compose.material.icons.twotone.Security
import androidx.compose.material.icons.twotone.Settings
import androidx.compose.material.icons.twotone.Terminal
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.linux_logic_app.R
import com.example.linux_logic_app.components.TerminalViewModel
import com.example.linux_logic_app.navigation.Screen
import com.example.linux_logic_app.ui.theme.LiloBlue
import com.example.linux_logic_app.ui.theme.LiloMain
import com.example.linux_logic_app.ui.theme.LiloOrange
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val endDrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val liloWebUrl = "https://www.linux-logic.com"
    val liloIntent = remember { Intent(Intent.ACTION_VIEW, Uri.parse(liloWebUrl)) }

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
                            imageVector = Icons.TwoTone.ManageAccounts,
                            contentDescription = "ManageAccounts Icon for Main",
                            tint = LiloMain
                        )
                        Text(
                            text = "Ihr Profil",
                            modifier = Modifier
                                .padding(16.dp),
                            style = MaterialTheme.typography.labelLarge
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
                                Icons.TwoTone.RateReview,
                                contentDescription = "RateReview Icon for Main",
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
                            navController.navigate(Screen.Start.route)
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
                                    /*coroutineScope.launch {
                                        endDrawerState.open()
                                    }*/
                                    navController.navigate(Screen.Profile.route)
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
                            containerColor = Color(0xFF569191),
                            scrolledContainerColor = Color(0xFF569191),
                            navigationIconContentColor = Color.White, // Hier setze ich die Farbe Weiß für die Icons ?
                            titleContentColor = Color.White,
                            actionIconContentColor = Color(0xFF569191)
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
            ) { innerPadding ->
                Box(
                    modifier = Modifier
                        .padding(innerPadding)
                ) {

                }
            }
        }
    }
}


