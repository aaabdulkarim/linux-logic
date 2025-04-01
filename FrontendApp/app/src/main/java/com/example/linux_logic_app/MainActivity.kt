package com.example.linux_logic_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.linux_logic_app.components.viewmodels.UserViewModel
import com.example.linux_logic_app.ui.theme.Linux_logic_appTheme
import com.example.linux_logic_app.navigation.*

/**
 * MainActivity - Die Hauptaktivität der Anwendung.
 * Diese Klasse setzt das Composable LinuxLogicNavigator mit einem UserViewModel.
 * Es wird sichergestellt, dass das Design mit dem App-Theme übereinstimmt.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Aktiviert Edge-to-Edge-Rendering für modernes UI-Design.
            enableEdgeToEdge()

            // Setzt das Theme für die gesamte App.
            Linux_logic_appTheme {
                val userViewModel: UserViewModel = viewModel()
                LinuxLogicNavigator(userViewModel = userViewModel)
            }
        }
    }
}

/**
 * LinuxLogicAppPreview - Eine Vorschau der Anwendung im Compose-Preview-Modus.
 * Hier wird das gleiche Design und Theme verwendet wie in der MainActivity,
 * um eine genaue Vorschau innerhalb des Android Studios zu ermöglichen.
 */
@Preview(showBackground = true)
@Composable
fun LinuxLogicAppPreview() {
    Linux_logic_appTheme {
        val userViewModel: UserViewModel = viewModel()
        LinuxLogicNavigator(userViewModel = userViewModel)
    }
}
