package com.example.linux_logic_app.screens

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.linux_logic_app.R

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun PlayScreen() {
    var selectedCourse by remember { mutableStateOf<Course?>(null) }

    SharedTransitionLayout(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            itemsIndexed(courseList) { _, course ->
                AnimatedVisibility(
                    visible = course != selectedCourse,
                    enter = fadeIn() + scaleIn(),
                    exit = fadeOut() + scaleOut(),
                    modifier = Modifier.animateItem()
                ) {
                    Box(
                        modifier = Modifier
                            .sharedBounds(
                                sharedContentState = rememberSharedContentState(key = "${course.name}-bounds"),
                                animatedVisibilityScope = this
                            )
                            .background(Color(0xFF569191), RoundedCornerShape(16.dp))
                            .clip(RoundedCornerShape(32.dp))
                    ) {
                        CourseCard(course = course, onClick = { selectedCourse = course })
                    }
                }
            }
        }

        CourseEditDetails(
            course = selectedCourse,
            onConfirmClick = { selectedCourse = null }
        )
    }
}

@Composable
fun CourseEditDetails(course: Course?, onConfirmClick: () -> Unit) {
    course?.let {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x80000000))
                .clickable { onConfirmClick() }
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .background(Color.White, RoundedCornerShape(16.dp))
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = it.imageRes),
                    contentDescription = it.name,
                    modifier = Modifier
                        .size(200.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .fillMaxWidth()
                )
                Text(it.name, style = MaterialTheme.typography.labelSmall)
                Spacer(modifier = Modifier.height(8.dp))
                Text(it.description, style = MaterialTheme.typography.bodySmall)
                Spacer(modifier = Modifier.height(16.dp))
                Row {
                    Button(
                        onConfirmClick,
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp)
                            .weight(1f),
                        contentPadding = PaddingValues(16.dp),
                        colors = ButtonDefaults.buttonColors().copy(
                            containerColor = Color(0xFF445a65),
                            contentColor = Color.White,
                            disabledContainerColor = Color(0xFFCECECE),
                            disabledContentColor = Color(0xFF7F7F7F)
                        ),
                    ) {
                        Text(
                            text = "Zurück",
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                    Button(
                        onConfirmClick,
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp)
                            .weight(1f),
                        contentPadding = PaddingValues(16.dp),
                        colors = ButtonDefaults.buttonColors().copy(
                            containerColor = Color(0xFFFF8c00),
                            contentColor = Color.White,
                            disabledContainerColor = Color(0xFFCECECE),
                            disabledContentColor = Color(0xFF7F7F7F)
                        ),
                    ) {
                        Text(
                            text = "Spielen",
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CourseCard(course: Course, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                onClick(

                )
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
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 70f
                        )
                    )
                    .height(80.dp)
            )

            // Text für den Kursnamen
            Text(
                text = course.name,
                style = MaterialTheme.typography.labelSmall,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
            )
        }
    }
}

data class Course(
    val name: String,
    val description: String,
    val imageRes: Int
)

val courseList = listOf(
    Course("Linux Basics", "Lernen Sie die Grundlagen von Linux.", R.drawable.linux_basics_course),
    Course("Advanced Bash", "Erstellen Sie leistungsstarke Bash-Skripte.", R.drawable.bash_course),
    Course("Docker Essentials", "Beherrschen Sie Docker-Container.", R.drawable.docker_course),
    Course("Linux Dateisystem und Navigation", "Erlernen Sie das Dateisystem und die Navigation unter Linux.", R.drawable.dateisystem_navigation_course),
    Course("Textbearbeitung mit vim und nano", "Erlernen Sie grundlegende Textbearbeitung unter Linux.", R.drawable.nano_vs_vim_course),
    Course("Linux Systemadministration", "Erlernen Sie die Grundlagen der Systemadministration unter Linux.", R.drawable.systemadministartion_course),
    Course("Netzwerkverwaltung unter Linux", "Verwalten Sie Netzwerke unter Linux.", R.drawable.netzwerkverwaltung_course)
)
