package com.example.linux_logic_app.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.linux_logic_app.navigation.Course
import com.example.linux_logic_app.navigation.courseList

@Composable
@OptIn(ExperimentalSharedTransitionApi::class)
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
                            .clip(RoundedCornerShape(16.dp))
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
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight(0.7f)
                    .background(Color.White, RoundedCornerShape(16.dp))
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = painterResource(id = it.imageRes),
                    contentDescription = it.name,
                    modifier = Modifier
                        .fillMaxWidth() // Use fillMaxWidth instead of weight
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    it.name,
                    style = MaterialTheme.typography.labelSmall
                )

                Text(
                    it.description,
                    modifier = Modifier
                        .padding(16.dp),
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(modifier = Modifier.weight(1f)) // Use weight to fill remaining space

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onConfirmClick,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
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
                            .padding(horizontal = 16.dp)
                            .weight(1f),
                        contentPadding = PaddingValues(16.dp),
                        colors = ButtonDefaults.buttonColors().copy(
                            containerColor = Color(0xFF569191),
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

@Composable
fun ExpandableText(
    text: String,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit
) {
    Column {
        Text(
            text = text,
            maxLines = if (expanded) Int.MAX_VALUE else 2,
            overflow = TextOverflow.Ellipsis
        )
        IconButton(onClick = { onExpandedChange(!expanded) }) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = if (expanded) "Ausblenden" else "Anzeigen"
            )
        }
    }
}
