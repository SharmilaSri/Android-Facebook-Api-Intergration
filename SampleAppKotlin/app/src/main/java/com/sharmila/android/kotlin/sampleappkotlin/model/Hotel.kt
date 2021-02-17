package com.sharmila.android.kotlin.sampleappkotlin.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/* Created By Sharmila Prasath
 * February 2021
 * */
data class Hotel (

        @SerializedName("id")
        @Expose
        var id: Int = 0,

        @SerializedName("title")
        @Expose
        var title: String = "",

        @SerializedName("description")
        @Expose
        var description: String = "",

        @SerializedName("address")
        @Expose
        var address: String = "",

        @SerializedName("postcode")
        @Expose
        var postcode: String = "",

        @SerializedName("phoneNumber")
        @Expose
        var phoneNumber: String = "",

        @SerializedName("latitute")
        @Expose
        var latitute: String = "",

        @SerializedName("longtitude")
        @Expose
        var longtitude: String = "",

        @SerializedName("image")
        @Expose
        var images: Image? = null

) {
    override fun toString(): String {
        return images.toString()
    }
    fun setTitle(title: String): Hotel {
        this.title = title
        return this
    }

    fun setDescription(description: String): Hotel {
        this.description = description
        return this
    }

    fun setLatitute(latitute: String): Hotel {
        this.latitute = latitute
        return this
    }

    fun setLongtitude(longtitude: String): Hotel {
        this.longtitude = longtitude
        return this
    }

    companion object {
        //Singleton design pattern
        private var hotelOb: Hotel? = null
        @JvmStatic
        val instance: Hotel?
            get() {
                if (hotelOb == null) {
                    hotelOb = Hotel()
                }
                return hotelOb
            }
    }
}