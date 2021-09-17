package com.olayg.onlykats.util

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.olayg.onlykats.model.request.Queries
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map

class UserManager(private val dataStore: DataStore<Preferences>) {

    companion object {
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

//    private val storedEndPoint: Flow<String?> = dataStore.data.map { preference ->
//        preference[ENDPOINT_KEY]
//    }
//    private val storedLimit: Flow<Int?> = dataStore.data.map { preference ->
//        preference[LIMIT_KEY]
//    }
//    private val storedPage: Flow<Int?> = dataStore.data.map { preference ->
//        preference[PAGE_KEY]
//    }

    fun getStoredValues(): Flow<Queries?> {
        return dataStore.data.map { it ->
            it[LIMIT_KEY]?.let { it1 ->
                Queries(
                    it[ENDPOINT_KEY]?.let { it1 -> EndPoint.valueOf(it1) },
                    it1,
                    it[PAGE_KEY]
                )
            }
        }
    }
}
