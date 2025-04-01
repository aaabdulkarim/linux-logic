package com.example.linux_logic_app.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.ModeEdit
import androidx.compose.material.icons.twotone.Palette
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.linux_logic_app.R

/**
 * Diese Datenklasse CustomizeItem, stellt eine Anpassungseinstellung für die App dar.
 * Jede Anpassung hat einen Namen, ein Icon (über eine Ressourcen-ID)
 * und eine optionale Beschreibung. (Für zukünftige Customization-Options)
 * @property name Der Name der Anpassung, z. B. "Farbe anpassen".
 * @property backgroundImage Eine optionale Ressourcen-ID des Icons, das für diese Anpassung verwendet wird.
 * @property description Eine optionale Beschreibung, die zusätzliche Informationen zu dieser Anpassung liefert.
 */
data class CustomizationItem(
    val name: String,                                   // Der Name der Anpassung (z. B. "Farbe anpassen")
    val backgroundImage: Int,                           // Ressourcen-ID für das Bild-Icon der Anpassung
    val icon: ImageVector = Icons.TwoTone.ModeEdit,     // Ein Icon für die jeweilige Anpassung
    val description: String                             // Eine Beschreibung für die Anpassung
)

/**
 * Eine Liste von `CustomizationItem`-Objekten, die verschiedene Anpassungen
 * für die App darstellen.
 * Diese Liste kann als Datenquelle für die Anzeige von Anpassungsoptionen
 * in der Benutzeroberfläche verwendet werden. Jede Anpassung hat einen Namen,
 * ein optionales Icon und eine optionale Beschreibung.
 */
val customizationList = listOf(
    /**
     * Eine Anpassung, bei der der Benutzer die Farbeinstellungen für das Terminal anpassen kann.
     * Sie enthält den Namen der Anpassung, eine Ressourcen-ID für das Bild-Icon und eine Beschreibung.
     */
    CustomizationItem(
        name = "Farbe anpassen",
        backgroundImage = R.drawable.colors_customize,
        icon = Icons.TwoTone.Palette,
        description = "Ein Bild zur Beschreibung der Anpassung für Farben im Terminal"
    )

    // Weitere Anpassungen können hier hinzugefügt werden, wenn erforderlich:
    /*
    CustomizationItem(
        name = "Anpassung 2",
        iconResId = R.drawable.my_local_image,   // Beispiel für eine Ressourcen-ID für ein weiteres Bild
        description = "Dies ist eine Beispielbeschreibung für Anpassung 2" // Beschreibung für eine weitere Anpassung
    ),
    CustomizationItem(
        name = "Anpassung 3",
        iconResId = R.drawable.my_local_image,   // Beispiel für eine weitere Bild-Ressourcen-ID
        description = "Dies ist eine Beispielbeschreibung für Anpassung 3" // Beschreibung für Anpassung 3
    )
    */
)
