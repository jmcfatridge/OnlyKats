package com.olayg.onlykats.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.olayg.onlykats.model.request.Queries
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class UserManager private constructor(private val dataStore: DataStore<Preferences>) {

    companion object {
        private var INSTANCE: UserManager? = null
        fun getInstance(context: Context?): UserManager {
            if (INSTANCE == null) if (context != null) {
                INSTANCE = UserManager(context.dataStore)
            }
            return INSTANCE!!
        }
        val ENDPOINT_KEY = stringPreferencesKey("ENDPOINT")
        val LIMIT_KEY = intPreferencesKey("LIMIT")
        val PAGE_KEY = intPreferencesKey("PAGE")
    }

    suspend fun updateValues(endpoint: String, limit: Int, page: Int) {
         dataStore.edit {
             it[ENDPOINT_KEY] = endpoint
             it[LIMIT_KEY] = limit
             it[PAGE_KEY] = page
         }
    }

    suspend fun updatePage(page: Int) {
        dataStore.edit {
            it[PAGE_KEY] = page
        }
    }

    fun getStoredValues(): Flow<Queries?> {
        return dataStore.data.map { it ->
            it[LIMIT_KEY]?.let { it1 ->
                Queries(
                    it[ENDPOINT_KEY]?.let { it2 -> EndPoint.valueOf(it2) },
                    it1,
                    it[PAGE_KEY]
                )
            }
        }
    }
}
