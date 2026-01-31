package com.example.hab

import android.app.Application
import android.util.Log
import com.example.hab.data.database.AppDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    // TAG
    private val TAG = "App"

    override fun onCreate() {
        super.onCreate()

        Log.d(TAG, "Application onCreate CALLED")
    }
}
