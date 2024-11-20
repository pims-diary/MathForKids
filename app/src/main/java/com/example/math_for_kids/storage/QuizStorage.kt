package com.example.math_for_kids.storage

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

// Define DataStore and Preference keys
val Context.dataStore by preferencesDataStore("user_prefs")
val QUIZ_LEVEL = intPreferencesKey("level")
val PLAYER_ID = stringPreferencesKey("id")

// Function to save level
suspend fun updateLevel(context: Context, level: Int) {
    context.dataStore.edit { preferences ->
        preferences[QUIZ_LEVEL] = level
    }
}

// Function to save player id
suspend fun savePlayerId(context: Context, id: String) {
    context.dataStore.edit { preferences ->
        preferences[PLAYER_ID] = id
    }
}

// Function to get data
fun getLevel(context: Context): Flow<Int> {
    return context.dataStore.data
        .catch { exception ->
            if (exception is IOException) emit(emptyPreferences())
        }
        .map { preferences ->
            preferences[QUIZ_LEVEL] ?: 1
        }
}

// Function to get data
fun getPlayerId(context: Context): Flow<String> {
    return context.dataStore.data
        .catch { exception ->
            if (exception is IOException) emit(emptyPreferences())
        }
        .map { preferences ->
            preferences[PLAYER_ID] ?: ""
        }
}