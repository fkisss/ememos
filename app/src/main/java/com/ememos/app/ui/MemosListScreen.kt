package com.ememos.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ememos.app.data.Memo
import com.ememos.app.data.MemosRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemosListScreen(
    repository: MemosRepository,
    onLogout: () -> Unit
) {
    var memos by remember { mutableStateOf(listOf<Memo>()) }
    var showCreateMemo by remember { mutableStateOf(false) }
    
    // 模拟数据
    if (memos.isEmpty()) {
        memos = listOf(
            Memo(
                id = "1",
                content = "这是一个示例笔记。您可以在这里写下任何想法或信息。",
                visibility = "PRIVATE",
                createdTs = System.currentTimeMillis() / 1000,
                updatedTs = System.currentTimeMillis() / 1000,
                creatorId = 1
            ),
            Memo(
                id = "2",
                content = "Memos是一个开源的自托管备忘录中心。这个Android客户端可以让您随时随地访问您的笔记。",
                visibility = "PUBLIC",
                createdTs = System.currentTimeMillis() / 1000 - 3600,
                updatedTs = System.currentTimeMillis() / 1000 - 1800,
                creatorId = 1
            )
        )
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("我的笔记") },
                actions = {
                    IconButton(onClick = onLogout) {
                        Icon(Icons.Default.Logout, contentDescription = "登出")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showCreateMemo = true }) {
                Icon(Icons.Default.Add, contentDescription = "创建笔记")
            }
        }
    ) { padding ->
        if (showCreateMemo) {
            // 在实际应用中，这应该是一个导航到新屏幕的操作
            MemoEditScreen(
                memo = null,
                repository = repository,
                onNavigateBack = { showCreateMemo = false }
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
            ) {
                // TODO: 从repository获取数据
                items(memos) { memo ->
                    MemoItem(memo = memo)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun MemoItem(memo: Memo) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = memo.content,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = when (memo.visibility) {
                        "PUBLIC" -> "公开"
                        "PRIVATE" -> "私有"
                        else -> "内部"
                    },
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary
                )
                Text(
                    text = "创建时间: ${memo.createdTs}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    }
}