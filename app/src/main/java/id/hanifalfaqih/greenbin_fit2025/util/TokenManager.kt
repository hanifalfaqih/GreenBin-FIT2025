package id.hanifalfaqih.greenbin_fit2025.util

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TokenManager(private val context: Context) {

    companion object {
        private val Context.dataStore by preferencesDataStore("user_preferences")
        private val TOKEN_KEY = stringPreferencesKey("token")
    }

    val tokenFlow: Flow<String?> = context.dataStore.data
        .map { preferences -> preferences[TOKEN_KEY] }

    suspend fun saveToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }

    suspend fun clearToken() {
        context.dataStore.edit { preferences ->
            preferences.remove(TOKEN_KEY)
        }
    }

}