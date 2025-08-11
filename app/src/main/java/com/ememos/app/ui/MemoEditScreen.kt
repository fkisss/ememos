package com.ememos.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ememos.app.data.Memo
import com.ememos.app.data.MemosRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemoEditScreen(
    memo: Memo?,
    repository: MemosRepository,
    onNavigateBack: () -> Unit
) {
    var content by remember { mutableStateOf(memo?.content ?: "") }
    var visibility by remember { mutableStateOf(memo?.visibility ?: "PRIVATE") }
    var isSaving by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (memo == null) "创建笔记" else "编辑笔记") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "返回")
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            isSaving = true
                            // TODO: 保存笔记
                            // 这里应该调用repository保存笔记
                            isSaving = false
                            onNavigateBack()
                        },
                        enabled = content.isNotBlank() && !isSaving
                    ) {
                        if (isSaving) {
                            CircularProgressIndicator(
                                color = MaterialTheme.colorScheme.onPrimary,
                                modifier = Modifier.size(24.dp)
                            )
                        } else {
                            Icon(Icons.Default.Save, contentDescription = "保存")
                        }
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            OutlinedTextField(
                value = content,
                onValueChange = { content = it },
                label = { Text("内容") },
                placeholder = { Text("写点什么...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                textStyle = MaterialTheme.typography.bodyLarge
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "可见性:",
                    style = MaterialTheme.typography.bodyLarge
                )
                
                Row {
                    RadioButton(
                        selected = visibility == "PUBLIC",
                        onClick = { visibility = "PUBLIC" }
                    )
                    Text("公开")
                    
                    Spacer(modifier = Modifier.width(8.dp))
                    
                    RadioButton(
                        selected = visibility == "PRIVATE",
                        onClick = { visibility = "PRIVATE" }
                    )
                    Text("私有")
                }
            }
        }
    }
}