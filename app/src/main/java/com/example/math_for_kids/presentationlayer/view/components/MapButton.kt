package com.example.math_for_kids.presentationlayer.view.components

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MapButton(latitude: Double, longitude: Double) {
    val context = LocalContext.current
    Button(
        onClick = {
            val gmmIntentUri = Uri.parse("geo:$latitude,$longitude")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")

            // Check if Google Maps is installed
            if (mapIntent.resolveActivity(context.packageManager) != null) {
                context.startActivity(mapIntent)
            } else {
                // Google Maps not installed, open Google Maps in Play Store
                try {
                    val playStoreIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=com.google.android.apps.maps")
                    )
                    context.startActivity(playStoreIntent)
                } catch (e: ActivityNotFoundException) {
                    // If Play Store is not found, open Google Maps in a web browser
                    val webIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps")
                    )
                    context.startActivity(webIntent)
                }
            }
        }) {
        Text("Open in Maps",
            modifier = Modifier.padding(10.dp),
            fontSize = 15.sp
        )
    }
}