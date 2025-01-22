package com.example.linux_logic_app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

@Composable
fun Terminal() {
    var terminalOutput by remember { mutableStateOf(listOf("Welcome to logic terminal!")) }
    var userInput by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    // Terminal UI
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFF404242), shape = RoundedCornerShape(16.dp)) // Rounded terminal box
        ) {
            // Header (separater Bereich oben)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black, shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)) // Rounded header top
                    .padding(12.dp)
            ) {
                Text(
                    text = "logic terminal",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold
                )
            }

            // Terminal Output and Input
            Box(
                modifier = Modifier
                    .weight(1f)
                    .background(Color(0xFF404242), shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)) // Rounded terminal bottom
                    .padding(8.dp)
                    .verticalScroll(scrollState)
            ) {
                Column {
                    // Terminal output
                    terminalOutput.forEach { line ->
                        Text(
                            text = line,
                            color = Color.White,
                            fontSize = 14.sp,
                            fontFamily = FontFamily.Monospace
                        )
                    }

                    // User Input
                    Row {
                        Text(
                            text = "lilo@beta:~\$ ",
                            color = Color.White,
                            fontSize = 14.sp,
                            fontFamily = FontFamily.Monospace
                        )
                        BasicTextField(
                            value = userInput,
                            onValueChange = { userInput = it },
                            textStyle = TextStyle(
                                color = Color.White,
                                fontSize = 14.sp,
                                fontFamily = FontFamily.Monospace
                            ),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    if (userInput.isNotBlank()) {
                                        terminalOutput = terminalOutput + "lilo@beta:~$ ${userInput}" + processCommand(userInput)
                                        userInput = "" // Clear input
                                    }
                                }
                            )
                        )
                    }
                }
            }
        }
    }
}

// Mock command processing function
fun processCommand(command: String): String {
    return when (command.lowercase()) {
        "help" -> "\rAvailable commands: help, clear, hello"
        "hello" -> "\rHello, User!"
        "ls" -> "home documents downloads" // Clear screen command (handled differently in a real terminal)
        else -> "\rCommand not found: $command"
    }
}
