package com.olayg.onlykats.repo.local


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.olayg.onlykats.adapter.KatConverters
import com.olayg.onlykats.model.Kat
import com.olayg.onlykats.repo.local.dao.KatDao


@Database(entities = [Kat::class], version = 1)
@TypeConverters(KatConverters::class)
abstract class KatDatabase : RoomDatabase() {
    abstract fun katDao(): KatDao

    companion object {

        private const val DATABASE_NAME = "kat.db"

        @Volatile
        private var instance: KatDatabase? = null

       fun getInstance(context: Context): KatDatabase {
           return instance ?: synchronized(this) {
               instance ?: buildDatabase(context).also {instance = it}
           }
       }

        private fun buildDatabase(context: Context): KatDatabase {
            return Room.databaseBuilder(
                context, KatDatabase::class.java, DATABASE_NAME
            ).build()
        }
    }
}