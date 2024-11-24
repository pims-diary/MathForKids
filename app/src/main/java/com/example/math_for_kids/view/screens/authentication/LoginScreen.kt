package com.example.math_for_kids.view.screens.authentication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.math_for_kids.navigations.AuthPages
import com.example.math_for_kids.storage.savePlayerId
import com.example.math_for_kids.view.components.AuthenticationForm
import com.example.math_for_kids.view.components.LinkButton
import com.example.math_for_kids.viewmodel.AuthenticationViewModel
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navHostController: NavHostController, authViewModel: AuthenticationViewModel) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AuthenticationForm("Login", authViewModel) { responseBody, isSuccess ->
            if (isSuccess) {
                scope.launch {
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