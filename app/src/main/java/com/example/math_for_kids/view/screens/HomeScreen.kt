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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.math_for_kids.navigations.AuthPages
import com.example.math_for_kids.storage.updateLevel
import com.example.math_for_kids.viewmodel.PlayerDetailsViewModel
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

    // Trigger getPlayer only once using LaunchedEffect
    LaunchedEffect(Unit) {
        playerDetailsViewModel.getPlayer("10001")
    }

    // Trigger the datastore update when response updates
    LaunchedEffect(response) {
        if (response.isNotEmpty()) {
            val levelString = response["level"] as? String ?: "0"
            val level = levelString.toIntOrNull() ?: 0
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

            Button(onClick = {
                navHostController.navigate(AuthPages.Login.route)
            }) {
                Text("Logout")
            }
        } else {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}
