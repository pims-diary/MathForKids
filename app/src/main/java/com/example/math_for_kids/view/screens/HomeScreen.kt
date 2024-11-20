package com.example.math_for_kids.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.math_for_kids.viewmodel.PlayerDetailsViewModel


data class Player(
    var id: String,
    var email: String,
    var name: String,
    var level: String
)

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    playerDetailsViewModel: PlayerDetailsViewModel = viewModel()
) {
    // Observe the state of responseBody directly
    val response = playerDetailsViewModel.responseBody
    val playerInfo = Player(
        id = "",
        email = "",
        name = "",
        level = ""
    )

    // Trigger getPlayer only once using LaunchedEffect
    LaunchedEffect(Unit) {
        playerDetailsViewModel.getPlayer("10001")
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
            Text("Welcome, $name!")
            Text("Email: $email")

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
