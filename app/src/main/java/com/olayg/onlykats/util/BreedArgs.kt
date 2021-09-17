package com.olayg.onlykats.util

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize


data class BreedArgs(
    val adaptability: Int?,
    val affectionLevel: Int?,
    val description: String?,
    val energyLevel: Int?,
    val id: String?,
    val intelligence: Int?,
    val lifeSpan: String?,
    val name: String?,
    val sheddingLevel: Int?,
    val vcaHospitalsUrl: String?,
    val vetStreetUrl: String?,
    val vocalisation: Int?,
    val wikipediaUrl: String?,
    val imageUrl: String?
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(adaptability)
        parcel.writeValue(affectionLevel)
        parcel.writeString(description)
        parcel.writeValue(energyLevel)
        parcel.writeString(id)
        parcel.writeValue(intelligence)
        parcel.writeString(lifeSpan)
        parcel.writeString(name)
        parcel.writeValue(sheddingLevel)
        parcel.writeString(vcaHospitalsUrl)
        parcel.writeString(vetStreetUrl)
        parcel.writeValue(vocalisation)
        parcel.writeString(wikipediaUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BreedArgs> {
        override fun createFromParcel(parcel: Parcel): BreedArgs {
            return BreedArgs(parcel)
        }

        override fun newArray(size: Int): Array<BreedArgs?> {
            return arrayOfNulls(size)
        }
    }
}


