package com.example.linux_logic_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.linux_logic_app.components.TerminalViewModel
import com.example.linux_logic_app.components.UserViewModel
import com.example.linux_logic_app.ui.theme.Linux_logic_appTheme
import com.example.linux_logic_app.navigation.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            enableEdgeToEdge()
            Linux_logic_appTheme {
                val userViewModel: UserViewModel = viewModel()
                val terminalViewModel: TerminalViewModel = viewModel()
                LinuxLogicNavigator(userViewModel = userViewModel, terminalViewModel = terminalViewModel)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LinuxLogicAppPreview() {
    Linux_logic_appTheme {
        val userViewModel: UserViewModel = viewModel()
        val terminalViewModel: TerminalViewModel = viewModel()
        LinuxLogicNavigator(userViewModel = userViewModel,  terminalViewModel = terminalViewModel)
    }
}
