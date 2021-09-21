package com.olayg.onlykats.repo.local.dao

import androidx.room.*
import com.olayg.onlykats.model.Kat
import kotlinx.coroutines.flow.Flow

@Dao
interface KatDao {

    @Query("SELECT * FROM kat")
    fun getAll(): Flow<List<Kat>>

    @Insert
    suspend fun insertAll(vararg kats: Kat)

    @Delete
    suspend fun delete(kat: Kat)
}