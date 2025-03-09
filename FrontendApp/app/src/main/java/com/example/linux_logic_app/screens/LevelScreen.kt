package com.example.linux_logic_app.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.twotone.ArrowBackIosNew
import androidx.compose.material.icons.twotone.SportsEsports
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.linux_logic_app.R
import com.example.linux_logic_app.ui.theme.LiloBlue
import com.example.linux_logic_app.ui.theme.LiloMain

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LevelScreen(navController: NavController) {
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
                            text = "Level",
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigateUp()
                        }
                    ) {
                        Icon(
                            Icons.TwoTone.ArrowBackIosNew,
                            contentDescription = "ArrowBackIosNew Icon for Notifications",
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
                            Icons.TwoTone.SportsEsports,
                            contentDescription = "NotificationsActive Icon for Notifications",
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
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
        ) {

        }
    }
}


@Composable
fun LevelCard() {
    var expanded by remember { mutableStateOf(false) }
    val rotationAngle by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        label = "Rotation of Arrow-Icon"
    )



    Card(
        colors = CardDefaults.cardColors(containerColor = LiloBlue)
    ) {
        Column(
            modifier = Modifier
                .clickable {
                    // Aktualisiert direkt im ViewModel!
                    //settingsViewModel.updateAccountCardExpanded(!expanded)
                    expanded = !expanded
                }
                .padding(16.dp)
                .animateContentSize()
                .imePadding(),  // Dieser Modifier fügt weiteren Platz hinzu, falls die Tastatur eingeblendet wird.
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    imageVector = Icons.TwoTone.SportsEsports,
                    contentDescription = "SportsEsports Icon for Level",
                    tint = LiloMain
                )
                Text(
                    text = "Name",
                    color = Color.White,
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold
                )
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowDown,
                    contentDescription = if (expanded) "Collapse" else "Expand",
                    modifier = Modifier.graphicsLayer(rotationZ = rotationAngle),
                    tint = Color.White
                )
            }

            if (expanded) {
                Spacer(modifier = Modifier.height(16.dp))


            }
        }
    }
}