package com.example.linux_logic_app.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.twotone.ArrowForward
import androidx.compose.material.icons.automirrored.twotone.ExitToApp
import androidx.compose.material.icons.twotone.AccountCircle
import androidx.compose.material.icons.twotone.Build
import androidx.compose.material.icons.twotone.Home
import androidx.compose.material.icons.twotone.Info
import androidx.compose.material.icons.twotone.Menu
import androidx.compose.material.icons.twotone.Notifications
import androidx.compose.material.icons.twotone.Place
import androidx.compose.material.icons.twotone.PlayArrow
import androidx.compose.material.icons.twotone.Search
import androidx.compose.material.icons.twotone.Settings
import androidx.compose.material.icons.twotone.ThumbUp
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.linux_logic_app.R
import com.example.linux_logic_app.navigation.Screen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val endDrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen,
        drawerContent = {
            ModalDrawerSheet {
                Text(
                    text = "Shortcuts",
                    modifier = Modifier
                        .padding(16.dp),
                    style = MaterialTheme.typography.labelLarge,
                )

                HorizontalDivider()

                NavigationDrawerItem(
                    label = {
                        Text(
                            text = "Suchen",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    },
                    selected = false,
                    icon = {
                        Icon(
                            Icons.TwoTone.Search,
                            contentDescription = "Search for Main"
                        )
                    },
                    onClick = {

                    }
                )

                HorizontalDivider()

                NavigationDrawerItem(
                    label = {
                        Text(
                            text = "Alle Level",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    },
                    selected = false,
                    icon = {
                        Icon(
                            Icons.AutoMirrored.TwoTone.ArrowForward,
                            contentDescription = "All levels for Main"
                        )
                    },
                    onClick = {

                    }
                )
            }
        }
    ) {
        ModalNavigationDrawer(
            drawerState = endDrawerState,
            gesturesEnabled = endDrawerState.isOpen,
            drawerContent = {
                ModalDrawerSheet {
                    Text(
                        text = "Dein Profil",
                        modifier = Modifier
                            .padding(16.dp),
                        style = MaterialTheme.typography.labelLarge,
                    )

                    HorizontalDivider()

                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = "Informationen",
                                style = MaterialTheme.typography.bodyLarge
                            )
                        },
                        selected = false,
                        icon = {
                            Icon(
                                Icons.TwoTone.Info,
                                contentDescription = "Information for Main"
                            )
                        },
                        onClick = {

                        }
                    )

                    HorizontalDivider()

                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = "Einstellungen",
                                style = MaterialTheme.typography.bodyLarge
                            )
                        },
                        selected = false,
                        icon = {
                            Icon(
                                Icons.TwoTone.Settings,
                                contentDescription = "Settings for Main"
                            )
                        },
                        onClick = {

                        }
                    )

                    HorizontalDivider()

                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = "Mitteilungen",
                                style = MaterialTheme.typography.bodyLarge
                            )
                        },
                        selected = false,
                        icon = {
                            Icon(
                                Icons.TwoTone.Notifications,
                                contentDescription = "Notifications for Main"
                            )
                        },
                        onClick = {

                        }
                    )

                    HorizontalDivider()

                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = "Hilfe und Feedback",
                                style = MaterialTheme.typography.bodyLarge
                            )
                        },
                        selected = false,
                        icon = {
                            Icon(
                                Icons.TwoTone.ThumbUp,
                                contentDescription = "Help and feedback for Main"
                            )
                        },
                        onClick = {

                        }
                    )

                    HorizontalDivider()

                    NavigationDrawerItem(
                        label = {
                            Text(
                                text = "Abmelden",
                                style = MaterialTheme.typography.bodyLarge
                            )
                        },
                        selected = false,
                        icon = {
                            Icon(
                                Icons.AutoMirrored.TwoTone.ExitToApp,
                                contentDescription = "Logout for Main"
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
                            Image(
                                painter = painterResource(id = R.drawable.linux_logic_pinguin),
                                contentDescription = "Linux Logic Pinguin",
                                modifier = Modifier
                                    .size(64.dp)
                            )

                            Spacer(modifier = Modifier.width(16.dp))

                            Image(
                                painter = painterResource(id = R.drawable.linux_logic_main_transparent_2),
                                contentDescription = "Linux Logic Logo",
                                modifier = Modifier
                                    .size(64.dp)
                            )
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
                                    contentDescription = "Profile Icon for Main",
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
                floatingActionButton = {
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
                },
                bottomBar = {
                    BottomAppBar(
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
                                modifier = Modifier

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
                    }
                }
            ) { innerPadding ->
                // Main Content
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Willkommen im Main Screen!", style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
    }
}
