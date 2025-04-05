package com.example.linux_logic_app.screens.gamification

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.linux_logic_app.components.scenario.Scenario
import com.example.linux_logic_app.components.scenario.scenarioList
import com.example.linux_logic_app.components.viewmodels.UserViewModel
import com.example.linux_logic_app.navigation.Screen
import com.example.linux_logic_app.ui.theme.LiloBlue
import com.example.linux_logic_app.ui.theme.LiloDarkText
import com.example.linux_logic_app.ui.theme.LiloMain

/**
 * PlayScreen - Der Bildschirm zum Auswählen und Starten eines Kurses (Scenario).
 * Dieses Composable zeigt eine Liste aller verfügbaren Kurse (courseList) in einer LazyColumn.
 * Jeder Kurs wird als CourseCard dargestellt und bei Auswahl animiert eingeblendet/ausgeblendet.
 * Beim Auswählen eines Kurses wird CourseEditDetails eingeblendet, um weitere Details anzuzeigen
 * und Optionen wie "Spielen" oder "Zurück" bereitzustellen.
 * Wichtige Aspekte:
 * - Verwendung von SharedTransitionLayout und AnimatedVisibility für fließende Übergänge.
 * - Die Animation der Kurskarten erfolgt mit fadeIn/scaleIn und fadeOut/scaleOut.
 * - Bei Auswahl eines Kurses wird dieser an das UserViewModel übergeben und der LevelScreen gestartet.
 * @param navController Steuert die Navigation zwischen den Screens.
 * @param userViewModel Verwaltet den Zustand des aktuell angemeldeten Nutzers.
 */
@Composable
@OptIn(ExperimentalSharedTransitionApi::class)
fun PlayScreen(navController: NavController, userViewModel: UserViewModel) {
    var selectedScenario by remember { mutableStateOf<Scenario?>(null) }

    SharedTransitionLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            itemsIndexed(scenarioList) { _, course ->
                AnimatedVisibility(
                    visible = course != selectedScenario,
                    enter = fadeIn() + scaleIn(),
                    exit = fadeOut() + scaleOut(),
                    modifier = Modifier.animateItem()
                ) {
                    Box(
                        modifier = Modifier
                            // SharedTransition für fließende Übergänge zwischen Kurskarten
                            .sharedBounds(
                                sharedContentState = rememberSharedContentState(key = "${course.name}-bounds"),
                                animatedVisibilityScope = this
                            )
                            .background(LiloMain, RoundedCornerShape(16.dp))
                            .clip(RoundedCornerShape(16.dp))
                    ) {
                        CourseCard(course = course, onSelectClick = { selectedScenario = course })
                    }
                }
            }
        }
        CourseEditDetails(
            course = selectedScenario,
            onPlayClick = {
                // Scenario auswählen und LevelViewModel im UserViewModel initialisieren
                userViewModel.selectScenarioForUser(selectedScenario)
                navController.navigate(Screen.Level.route)
            },
            onCancelClick = { selectedScenario = null }
        )
    }
}

/**
 * CourseEditDetails - Zeigt Detailinformationen zu einem ausgewählten Kurs an.
 * Dieses Composable wird angezeigt, wenn ein Kurs ausgewählt wurde.
 * Es zeigt das Kursbild, den Kursnamen und die Kursbeschreibung.
 * Außerdem stehen Buttons zum Starten des Kurses ("Spielen") und zum Abbrechen der Auswahl ("Zurück") bereit.
 * Wichtige Aspekte:
 * - Animierte Erweiterung der Kursdetails durch Klicken, gesteuert über einen Drehwinkel für das Pfeilsymbol.
 * - Die Kursbeschreibung wird in einer LazyColumn dargestellt, um auch lange Texte anzuzeigen.
 * @param course Das aktuell ausgewählte Scenario, oder null, wenn kein Kurs ausgewählt wurde.
 * @param onPlayClick Callback zum Starten des Kurses.
 * @param onCancelClick Callback zum Abbrechen der Kursauswahl.
 */
@Composable
fun CourseEditDetails(
    course: Scenario?,
    onPlayClick: () -> Unit,
    onCancelClick: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val rotationAngle by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        label = "Rotation of Arrow-Icon"
    )

    course?.let {
        // Äußerer Container: Klickt man hier, soll bei erweitertem Zustand nur geschlossen werden
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x80000000))
                .clickable {
                    // Wenn Details expandiert sind, beim Klicken schließen – sonst nichts tun
                    if (expanded) expanded = false
                }
                .padding(16.dp)
        ) {
            // Die Card selbst soll nicht auf Klicks vom Hintergrund reagieren,
            // daher verhindern wir die Eventweitergabe mit clickable(enabled = false)
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background, RoundedCornerShape(16.dp))
                    .align(Alignment.Center)
                    .clickable(enabled = false) { },
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painterResource(id = it.imageRes),
                    contentDescription = it.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
                    contentScale = ContentScale.FillWidth
                )

                Spacer(modifier = Modifier.height(16.dp))

                Column(
                    modifier = Modifier
                        .clickable {
                            // Umschalten des expandierten Zustands
                            expanded = !expanded
                        }
                        .animateContentSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowDropDown,
                            contentDescription = if (expanded) "Collapse" else "Expand",
                            modifier = Modifier.graphicsLayer(rotationZ = rotationAngle),
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                        Text(
                            it.name,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        item {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = it.description,
                                    modifier = Modifier.padding(horizontal = 16.dp),
                                    maxLines = if (expanded) Int.MAX_VALUE else 2,
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.onBackground,
                                    textAlign = TextAlign.Justify
                                )
                            }
                        }
                        item {
                            // Die Buttons erscheinen innerhalb des expandierten Bereichs
                            // und lösen hier das jeweilige Callback aus
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Button(
                                    onClick = onCancelClick,
                                    modifier = Modifier
                                        .padding(horizontal = 8.dp)
                                        .weight(1f),
                                    contentPadding = PaddingValues(16.dp),
                                    colors = ButtonDefaults.buttonColors().copy(
                                        containerColor = LiloBlue,
                                        contentColor = LiloDarkText,
                                        disabledContainerColor = Color(0xFFCECECE),
                                        disabledContentColor = Color(0xFF7F7F7F)
                                    ),
                                ) {
                                    Text(
                                        text = "Zurück",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = LiloDarkText
                                    )
                                }
                                Button(
                                    onClick = onPlayClick,
                                    modifier = Modifier
                                        .padding(horizontal = 8.dp)
                                        .weight(1f),
                                    contentPadding = PaddingValues(16.dp),
                                    colors = ButtonDefaults.buttonColors().copy(
                                        containerColor = LiloMain,
                                        contentColor = LiloDarkText,
                                        disabledContainerColor = Color(0xFFCECECE),
                                        disabledContentColor = Color(0xFF7F7F7F)
                                    ),
                                ) {
                                    Text(
                                        text = "Spielen",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = LiloDarkText
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

/**
 * CourseCard - Stellt eine einzelne Kurskarte dar.
 * Dieses Composable zeigt den Kurs als Card mit Hintergrundbild, einem Gradienten-Overlay
 * und dem Kursnamen. Beim Klicken auf die Card wird der Kurs als ausgewählt markiert.
 * Wichtige Aspekte:
 * - Die Card hat abgerundete Ecken und einen leichten Schatten (Elevation).
 * - Das Bild wird skaliert und mit einem Overlay versehen, um den Text lesbar zu machen.
 * @param course Das anzuzeigende Scenario.
 * @param onSelectClick Callback, der beim Klicken auf die Card aufgerufen wird.
 */
@Composable
fun CourseCard(course: Scenario, onSelectClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onSelectClick()
            },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .height(200.dp)
                .fillMaxWidth()
        ) {
            // Hintergrundbild
            Image(
                painter = painterResource(id = course.imageRes),
                contentDescription = course.name,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            // Gradient-Overlay
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent, Color.Black
                            ), startY = 70f
                        )
                    )
                    .height(80.dp)
            )

            // Text für den Kursnamen
            Text(
                text = course.name,
                style = MaterialTheme.typography.labelSmall,
                color = LiloDarkText,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
            )
        }
    }
}
