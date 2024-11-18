package com.example.math_for_kids.view.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.math_for_kids.model.MenuItemDetails
import com.example.math_for_kids.navigations.QuizScreensStack
import com.example.math_for_kids.view.components.MenuItem
import com.example.math_for_kids.view.components.MenuTopBar
import com.example.math_for_kids.viewmodel.PlayerDetailsViewModel
import kotlinx.coroutines.launch

///List of Navigation Items that will be clicked
val menuItems = listOf(
    MenuItemDetails(
        title = "Home",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home
    ),
    MenuItemDetails(
        title = "Play",
        selectedIcon = Icons.Filled.PlayArrow,
        unselectedIcon = Icons.Outlined.PlayArrow
    )
)

@Composable
fun AppLandingScreen(
    navHostController: NavHostController,
    playerDetailsViewModel: PlayerDetailsViewModel = viewModel()
) {
    //Remember Clicked index state and the Route Screen
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }
    var route by remember {  mutableStateOf("home") }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // Collect player details from non-sql HTTP request
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        scope.launch {
            playerDetailsViewModel.getPlayer("10001")
            isLoading = false
        }
    }

    if (isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet {
                    Spacer(modifier = Modifier.height(16.dp)) //space (margin) from top
                    menuItems.forEachIndexed { index, item ->
                        MenuItem(
                            index = index,
                            selectedItemIndex = selectedItemIndex,
                            item = item
                        ) {
                            selectedItemIndex = index
                            scope.launch {
                                drawerState.close()
                            }
                            // Route string set here to help navigation
                            when (selectedItemIndex) {
                                0 -> route = "home"
                                1 -> route = "play"
                            }
                        }
                    }
                }
            },
            gesturesEnabled = true
        ) {
            MenuTopBar {
                scope.launch {
                    drawerState.apply {
                        if (isClosed) open() else close()
                    }
                }
            }

            // Navigation completed here
            when (route) {
                "home" -> HomeScreen()
                "play" -> QuizScreensStack()
            }
        }
    }
}