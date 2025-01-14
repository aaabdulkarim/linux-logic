package com.example.linux_logic_app.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun Terminal() {
    // Terminal state
    var terminalOutput by remember { mutableStateOf(listOf("Welcome to Linux Logic Terminal!")) }
    var currentInput by remember { mutableStateOf(TextFieldValue("")) }

    Column(modifier = Modifier.fillMaxSize().background(Color.Black)) {
        // Terminal output
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            items(terminalOutput.size) { index ->
                Text(
                    text = terminalOutput[index],
                    color = Color.Green,
                    modifier = Modifier.padding(2.dp)
                )
            }
        }

        // User input field
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.DarkGray)
                .padding(8.dp)
        ) {
            BasicTextField(
                value = currentInput,
                onValueChange = { currentInput = it },
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
                    .background(Color.Black, MaterialTheme.shapes.small)
                    .padding(8.dp),
                textStyle = LocalTextStyle.current.copy(color = Color.White)
            )
            Button(onClick = {
                if (currentInput.text.isNotBlank()) {
                    // Simulate command processing
                    terminalOutput = terminalOutput + "$ ${currentInput.text}" +
                            processCommand(currentInput.text)
                    currentInput = TextFieldValue("") // Clear input
                }
            }) {
                Text("Run")
            }
        }
    }
}

// A simple mock function to simulate command processing
fun processCommand(input: String): String {
    return when (input.lowercase()) {
        "help" -> "Available commands: help, clear, hello"
        "hello" -> "Hello, User!"
        "clear" -> ""
        else -> "Command not found: $input"
    }
}