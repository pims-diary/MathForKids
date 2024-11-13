package com.example.math_for_kids

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
 import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.math_for_kids.ui.theme.Math_For_KidsTheme
import com.example.math_for_kids.view.screens.AppLandingScreen
import com.example.math_for_kids.view.screens.SplashScreen
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Math_For_KidsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var isLoading by remember { mutableStateOf(true) }

                    LaunchedEffect(Unit) {
                        delay(4000) // Display the splash screen for 2 seconds
                        isLoading = false // Navigate to the main content
                    }

                    // Use default splash screen if the device is running Android 12 or later
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                        AppLandingScreen()
                    } else {
                        if (isLoading) {
                            SplashScreen()
                        } else {
                            AppLandingScreen()
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Math_For_KidsTheme {
        AppLandingScreen()
    }
}