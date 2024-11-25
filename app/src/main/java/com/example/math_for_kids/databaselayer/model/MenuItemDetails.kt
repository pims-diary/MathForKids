package com.example.math_for_kids.databaselayer.model

import androidx.compose.ui.graphics.vector.ImageVector

// Create Menu Items Class to Select Unselect items
data class MenuItemDetails(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeCount: Int? = null
)
