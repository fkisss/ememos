package com.ememos.app.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MemosRepository(private val context: Context) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "memos_settings")
    
    companion object {
        val API_URL_KEY = stringPreferencesKey("api_url")
        val ACCESS_TOKEN_KEY = stringPreferencesKey("access_token")
    }
    
    val apiUrl: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[API_URL_KEY] ?: ""
        }
    
    val accessToken: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[ACCESS_TOKEN_KEY] ?: ""
        }
    
    suspend fun saveCredentials(apiUrl: String, accessToken: String) {
        context.dataStore.edit { preferences ->
            preferences[API_URL_KEY] = apiUrl
            preferences[ACCESS_TOKEN_KEY] = accessToken
        }
    }
    
    suspend fun clearCredentials() {
        context.dataStore.edit { preferences ->
            preferences.remove(API_URL_KEY)
            preferences.remove(ACCESS_TOKEN_KEY)
        }
    }
    
    // 获取备忘录列表
    suspend fun getMemos(): List<Memo> {
        // TODO: 实现从API获取数据的逻辑
        return emptyList()
    }
    
    // 创建备忘录
    suspend fun createMemo(content: String, visibility: String): Memo? {
        // TODO: 实现创建备忘录的逻辑
        return null
    }
    
    // 更新备忘录
    suspend fun updateMemo(id: String, content: String, visibility: String): Memo? {
        // TODO: 实现更新备忘录的逻辑
        return null
    }
    
    // 删除备忘录
    suspend fun deleteMemo(id: String): Boolean {
        // TODO: 实现删除备忘录的逻辑
        return false
    }
}