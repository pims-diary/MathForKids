package com.example.math_for_kids.presentationlayer.view.screens.other

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.math_for_kids.presentationlayer.view.components.DialButton
import com.example.math_for_kids.presentationlayer.view.components.MapButton

@Composable
fun AboutScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("About")
        Spacer(Modifier.height(16.dp))
        DialButton("+64 28 430 7422")
        Spacer(Modifier.height(8.dp))
        MapButton(latitude = 37.7749, longitude = -122.4194)
    }
}