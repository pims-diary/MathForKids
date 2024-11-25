package com.example.math_for_kids.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.math_for_kids.navigations.AuthPages
import com.example.math_for_kids.databaselayer.storage.getPlayerId
import com.example.math_for_kids.databaselayer.storage.savePlayerId
import com.example.math_for_kids.databaselayer.storage.updateLevel
import com.example.math_for_kids.viewmodel.PlayerDetailsViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    playerDetailsViewModel: PlayerDetailsViewModel = viewModel()
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    // Observe the state of responseBody directly
    val response = playerDetailsViewModel.responseBody
    var playerId by remember { mutableStateOf("") }

    // Get Player ID from sharedPreference dataStore
    LaunchedEffect(Unit) {
        scope.launch {
            playerId = getPlayerId(context).first()
        }
    }

    // Wait until Player ID is received from dataStore
    LaunchedEffect(playerId) {
        if (playerId.isNotEmpty()) { // Ensure playerId is not empty before calling getPlayer
            // Make get-player API call
            scope.launch {
                playerDetailsViewModel.getPlayer(playerId)
            }
        }
    }

    // Wait for API response
    LaunchedEffect(response) {
        if (response.isNotEmpty()) {
            // Extract Player Level from API response
            val levelString = response["level"] as? String ?: "0"
            // Convert Player Level to Int
            val level = levelString.toIntOrNull() ?: 0
            // Save Player Level in sharedPreference dataStore
            scope.launch {
                updateLevel(context, level)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (response != emptyMap<String, Any>()) {
            // Safely access keys in the response map
            val name = response["username"] as? String ?: "Unknown"
            val email = response["email"] as? String ?: "Unknown"
            val levelString = response["level"] as? String ?: "0"
            Text("Welcome, $name!")
            Text("Email: $email")
            Text("Level: $levelString")
            Text("Player ID: $playerId")

            Button(onClick = {
                // Clear dataStore on Logout
                scope.launch {
                    updateLevel(context, 0)
                    savePlayerId(context, "")
                    navHostController.navigate(AuthPages.Login.route)
                }
            }) {
                Text("Logout")
            }
        } else {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                // Show Loading Indicator until API response is received
                CircularProgressIndicator()
            }
        }
    }
}
