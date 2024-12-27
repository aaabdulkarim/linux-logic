package com.example.linux_logic_app.screens

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.linux_logic_app.R

@Composable
fun StartScreen() {
    Image(painter = painterResource(id = R.drawable.linux_logic_start), contentDescription = "Linux Logic Start Screen")
}