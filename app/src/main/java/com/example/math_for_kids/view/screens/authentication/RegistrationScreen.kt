package com.example.math_for_kids.view.screens.authentication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.math_for_kids.navigations.AuthPages
import com.example.math_for_kids.view.components.AuthenticationForm
import com.example.math_for_kids.view.components.LinkButton
import com.example.math_for_kids.viewmodel.AuthenticationViewModel

@Composable
fun RegistrationScreen(navHostController: NavHostController, authViewModel: AuthenticationViewModel) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AuthenticationForm("Register", authViewModel) { response, isSuccess ->
            if (isSuccess) {
                navHostController.navigate(AuthPages.Login.route)
            }
        }
        LinkButton(
            text = "Log In Instead",
            onClick = {
                navHostController.navigate(AuthPages.Login.route)
            }
        )
    }
}