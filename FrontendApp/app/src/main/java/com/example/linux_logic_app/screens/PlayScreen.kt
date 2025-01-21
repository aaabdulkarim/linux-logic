package com.example.linux_logic_app.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.linux_logic_app.R

@Composable
fun PlayScreen() {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp)
    ) {
        CourseListScreen()
    }

}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun CourseListScreen() {
    var selectedCourse by remember { mutableStateOf<Course?>(null) }

    SharedTransitionLayout(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            itemsIndexed(courseList) { index, course ->
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
                                animatedVisibilityScope = this,
                                clipInOverlayDuringTransition = OverlayClip(RoundedCornerShape(12.dp))
                            )
                            .background(Color.White, RoundedCornerShape(12.dp))
                            .clip(RoundedCornerShape(12.dp))
                    ) {
                        CourseContents(
                            course = course,
                            modifier = Modifier.sharedElement(
                                state = rememberSharedContentState(key = course.name),
                                animatedVisibilityScope = this@AnimatedVisibility
                            ),
                            onClick = {
                                selectedCourse = course
                            }
                        )
                    }
                }
            }
        }
        CourseEditDetails(
            course = selectedCourse,
            onConfirmClick = {
                selectedCourse = null
            }
        )
    }
}

@Composable
fun CourseContents(course: Course, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = course.imageRes),
            contentDescription = course.name,
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(Color.Gray)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(course.name, style = MaterialTheme.typography.bodyMedium)
            Text(course.description, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
        }
    }
}

@Composable
fun CourseEditDetails(course: Course?, onConfirmClick: () -> Unit) {
    if (course != null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x80000000))
                .clickable { onConfirmClick() }
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(32.dp)
                    .background(Color.White, RoundedCornerShape(16.dp))
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = course.imageRes),
                    contentDescription = course.name,
                    modifier = Modifier
                        .size(128.dp)
                        .clip(CircleShape)
                        .background(Color.Gray)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(course.name, style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(course.description, style = MaterialTheme.typography.bodySmall)
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = onConfirmClick) {
                    Text("Zurück")
                }
            }
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
    Course("Linux Dateisystem und Navigation", "Beherrschen Sie Docker-Container.", R.drawable.dateisystem_navigation_course),
    Course("Textbearbeitung mit vim und nano", "Beherrschen Sie Docker-Container.", R.drawable.nano_vs_vim_course),
    Course("Linux Systemadministration", "Beherrschen Sie Docker-Container.", R.drawable.systemadministartion_course),
    Course("Netzwerkverwaltung unter Linux", "Beherrschen Sie Docker-Container.", R.drawable.netzwerkverwaltung_course)
)
