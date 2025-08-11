package com.ememos.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ememos.app.data.MemosRepository
import com.ememos.app.ui.LoginScreen
import com.ememos.app.ui.MemosListScreen

@Composable
fun EMemosApp() {
    val context = LocalContext.current
    val navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    val isLoggedIn = remember { mutableStateOf(false) }
    val memosRepository = remember { MemosRepository(context) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        NavHost(navController = navController, startDestination = "login") {
            composable("login") {
                LoginScreen(
                    repository = memosRepository,
                    onLoginSuccess = {
                        isLoggedIn.value = true
                        navController.navigate("memos") {
                            popUpTo("login") { inclusive = true }
                        }
                    }
                )
            }
            composable("memos") {
                MemosListScreen(
                    repository = memosRepository,
                    onLogout = {
                        coroutineScope.launch {
                            memosRepository.clearCredentials()
                        }
                        isLoggedIn.value = false
                        navController.navigate("login") {
                            popUpTo("memos") { inclusive = true }
                        }
                    }
                )
            }
        }
    }
}