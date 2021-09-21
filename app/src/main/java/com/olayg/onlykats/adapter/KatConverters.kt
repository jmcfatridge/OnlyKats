package com.olayg.onlykats.adapter

import androidx.room.TypeConverter
import com.olayg.onlykats.model.Breed
import com.olayg.onlykats.model.Category
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class KatConverters {

    private val moshi by lazy { Moshi.Builder().build() }

    @TypeConverter
    fun stringToBreedList(data: String?): List<Breed> {
        return data?.let { getGenericAdapter<Breed>().fromJson(it) } ?: emptyList()
    }

    @TypeConverter
    fun breedListToString(someObjects: List<Breed>?): String? {
        return getGenericAdapter<Breed>().toJson(someObjects)
    }

    @TypeConverter
    fun stringToCategoryList(data: String?): List<Category> {
        return data?.let { getGenericAdapter<Category>().fromJson(it) } ?: emptyList()
    }

    @TypeConverter
    fun categoryListToString(someObjects: List<Category>?): String? {
        return getGenericAdapter<Category>().toJson(someObjects)
    }

    private inline fun <reified T> getGenericAdapter(): JsonAdapter<List<T>> {
        val type = Types.newParameterizedType(List::class.java, T::class.java)
        return moshi.adapter(type)
    }
}