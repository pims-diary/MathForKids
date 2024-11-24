package com.example.math_for_kids.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.math_for_kids.viewmodel.AuthenticationViewModel

@Composable
fun AuthenticationForm(
    buttonName: String,
    viewModel: AuthenticationViewModel,
    onSuccess: (Map<String, Any>, Boolean) -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }

    // React to changes in isSuccess and responseBody
    LaunchedEffect(viewModel.isSuccess) {
        if (viewModel.isSuccess) {
            onSuccess(viewModel.responseBody, viewModel.isSuccess)
        }
    }

    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        BasicTextField(
            value = email,
            onValueChange = {
                email = it
            },
            labelText = "Email",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        Spacer(modifier = Modifier.height(8.dp))
        BasicTextField(
            value = password,
            onValueChange =
            {
                password = it
            },
            labelText = "Password",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation()
        )
        if (buttonName == "Register") {
            Spacer(modifier = Modifier.height(8.dp))
            BasicTextField(
                value = name,
                onValueChange =
                {
                    name = it
                },
                labelText = "Name",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            if (buttonName == "Register") {
                viewModel.register(email, password, name)
            } else if (buttonName == "Login") {
                viewModel.login(email, password)
            }
        }) {
            Text(buttonName)
        }
        if (viewModel.errorMessage.isNotBlank()) {
            Spacer(Modifier.height(6.dp))
            Text(viewModel.errorMessage, color = Color.Red)
        }
    }
}