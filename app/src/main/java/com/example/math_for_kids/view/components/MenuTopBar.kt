package com.example.math_for_kids.view.components

import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuTopBar(onClick: () -> Unit) {
    Scaffold(
        topBar = { //TopBar to show title
            TopAppBar(
                title = {
                    Text(text = "MATH FOR KIDS")
                },
                navigationIcon = {
                    IconButton(onClick = onClick) {
                        Icon(  //Show Menu Icon on TopBar
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu"
                        )
                    }
                }
            )
        }
    ) {
    }
}