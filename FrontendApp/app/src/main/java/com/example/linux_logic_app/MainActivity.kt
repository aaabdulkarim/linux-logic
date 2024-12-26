package com.example.linux_logic_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.linux_logic_app.ui.theme.Linux_logic_appTheme

import com.example.linux_logic_app.screens.LoginScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Linux_logic_appTheme {
                LoginScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LinuxLogicAppPreview() {
    Linux_logic_appTheme {

    }
}
