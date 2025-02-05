package com.example.linux_logic_app.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardReturn
import androidx.compose.material.icons.automirrored.filled.Shortcut
import androidx.compose.material.icons.twotone.Category
import androidx.compose.material.icons.twotone.DesignServices
import androidx.compose.material.icons.twotone.Info
import androidx.compose.material.icons.twotone.Menu
import androidx.compose.material.icons.twotone.Search
import androidx.compose.material.icons.twotone.Security
import androidx.compose.material.icons.twotone.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwitchDefaults
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
import com.example.linux_logic_app.ui.theme.LiloBlue
import com.example.linux_logic_app.ui.theme.LiloMain
import com.example.linux_logic_app.ui.theme.LiloOrange
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

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
                )
            ) {
                Row(
                    modifier = Modifier
                        .padding(start = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.TwoTone.Category,
                        contentDescription = "Shortcut Icon for Settings",
                        tint = LiloMain
                    )

                    Text(
                        text = "Kategorien",
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
                            contentDescription = "Security Icon for Settings",
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
                            text = "Design",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    },
                    selected = false,
                    icon = {
                        Icon(
                            Icons.TwoTone.DesignServices,
                            contentDescription = "Search Icon for Settings",
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

                    }
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically, // Row vertikal zentrieren
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.linux_logic_pinguin),
                                contentDescription = "Linux Logic Pinguin",
                            )

                            Text(
                                text = "Einstellungen",
                                style = MaterialTheme.typography.labelMedium
                            )
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
                                contentDescription = "Hamburger Menu for Settings",
                                tint = Color.White
                            )
                        }
                    },
                    actions = {
                        IconButton(
                            onClick = {
                                // empty
                            }
                        ) {
                            Icon(
                                Icons.TwoTone.Settings,
                                contentDescription = "Settings Icon for Settings",
                                tint = Color.White
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
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        navController.navigate(Screen.Main.route)
                    },
                    containerColor = LiloOrange,
                    contentColor = Color.White,
                    shape = RoundedCornerShape(100)
                ) {
                    Icon(
                        Icons.AutoMirrored.Default.KeyboardReturn,
                        contentDescription = "ArrowCircleLeft Icon for Settings"
                    )
                }
            },
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
            ) {

            }
        }
    }
}


