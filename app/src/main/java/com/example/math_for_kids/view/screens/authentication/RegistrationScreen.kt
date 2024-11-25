package com.example.math_for_kids.view.screens.authentication

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.math_for_kids.navigations.AuthPages
import com.example.math_for_kids.view.components.AuthenticationForm
import com.example.math_for_kids.view.components.LinkButton
import com.example.math_for_kids.middlelayer.viewmodel.AuthenticationViewModel

@Composable
fun RegistrationScreen(navHostController: NavHostController, authViewModel: AuthenticationViewModel = viewModel()) {
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AuthenticationForm("Register", authViewModel) { response, isSuccess ->
            if (isSuccess) {
                Toast.makeText(context, response["message"].toString(), Toast.LENGTH_SHORT).show()
            }
        }
        LinkButton(
            text = "Go back to Login",
            onClick = {
                navHostController.navigate(AuthPages.Login.route)
            }
        )
    }
}