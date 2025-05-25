package com.example.workout_timer_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import com.example.workout_timer_app.ui.screen.RoutineScreen
import com.example.workout_timer_app.ui.theme.WorkouttimerappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkouttimerappTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    RoutineScreen()
                }
            }
        }
    }
}