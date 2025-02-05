package com.example.linux_logic_app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Hier kannst du globale Initialisierungen vornehmen
        // Zum Beispiel: Analytics, Logging, etc.

        // Falls du mit einer Library wie Firebase, Crashlytics, etc. arbeitest:
        // FirebaseApp.initializeApp(this)
    }
}