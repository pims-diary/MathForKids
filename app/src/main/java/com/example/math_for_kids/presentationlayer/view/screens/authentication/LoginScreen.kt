package com.example.math_for_kids.presentationlayer.view.screens.authentication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.math_for_kids.presentationlayer.navigations.AuthPages
import com.example.math_for_kids.databaselayer.storage.getPlayerId
import com.example.math_for_kids.databaselayer.storage.savePlayerId
import com.example.math_for_kids.presentationlayer.view.components.AuthenticationForm
import com.example.math_for_kids.presentationlayer.view.components.LinkButton
import com.example.math_for_kids.middlelayer.viewmodel.AuthenticationViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navHostController: NavHostController, authViewModel: AuthenticationViewModel = viewModel()) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    var playerId by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        playerId = getPlayerId(context).first()
    }

    LaunchedEffect(playerId) {
        if (playerId.isNotBlank()) {
            navHostController.navigate(AuthPages.AppLanding.route)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AuthenticationForm("Login", authViewModel) { responseBody, isSuccess ->
            if (isSuccess) {
                scope.launch {
                    playerId = responseBody["PlayerId"].toString()
                    savePlayerId(context, responseBody["PlayerId"].toString())
                    navHostController.navigate(AuthPages.AppLanding.route)
                }
            }
        }
        LinkButton(
            text = "Register Now",
            onClick = {
                navHostController.navigate(AuthPages.Registration.route)
            }
        )
    }
}