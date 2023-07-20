package com.peonlee.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val datastore: DataStore<Preferences>) {
    fun getAccessToken() : Flow<String> = datastore.data.catch { exception ->
        if(exception is IOException) {
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.map { preference -> preference[USER_ACCESS_TOKEN] ?: "" }

    suspend fun setAccessToken(accessToken: String) {
        datastore.edit { preference ->
            try {
                preference[USER_ACCESS_TOKEN] = accessToken
            } catch (exception: IOException) {
                emptyPreferences()
            }
        }
    }

    companion object {
        val USER_ACCESS_TOKEN = stringPreferencesKey("user_access_token")
    }
}
