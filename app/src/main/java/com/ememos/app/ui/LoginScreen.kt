package com.ememos.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.ememos.app.data.MemosRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    repository: MemosRepository,
    onLoginSuccess: () -> Unit
) {
    var apiUrl by remember { mutableStateOf("") }
    var accessToken by remember { mutableStateOf("") }
    var isLoggingIn by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "登录到 Memos",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        OutlinedTextField(
            value = apiUrl,
            onValueChange = { apiUrl = it },
            label = { Text("API URL") },
            placeholder = { Text("例如: https://your-memos.com") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = accessToken,
            onValueChange = { accessToken = it },
            label = { Text("访问令牌") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
        )

        errorMessage?.let {
            Text(
                text = it,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        Button(
            onClick = {
                if (apiUrl.isNotBlank() && accessToken.isNotBlank()) {
                    isLoggingIn = true
                    errorMessage = null
                    
                    // 保存凭证
                    // 注意：在实际应用中，这里应该验证凭证是否有效
                    // 暂时直接保存并跳转
                    /*
                    coroutineScope.launch {
                        repository.saveCredentials(apiUrl, accessToken)
                        withContext(Dispatchers.Main) {
                            isLoggingIn = false
                            onLoginSuccess()
                        }
                    }
                    */
                    
                    // 临时直接跳转
                    isLoggingIn = false
                    onLoginSuccess()
                } else {
                    errorMessage = "请填写所有字段"
                }
            },
            enabled = apiUrl.isNotBlank() && accessToken.isNotBlank() && !isLoggingIn,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            if (isLoggingIn) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(24.dp)
                )
            } else {
                Text("登录")
            }
        }
    }
}