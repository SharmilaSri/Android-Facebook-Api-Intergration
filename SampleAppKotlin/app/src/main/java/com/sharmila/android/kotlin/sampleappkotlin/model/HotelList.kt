package com.sharmila.android.kotlin.sampleappkotlin.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/* Created By Sharmila Prasath
 * February 2021
 * */
data class HotelList(
        @SerializedName("data")
        @Expose
        val hotels: List<Hotel>? = null){

        override fun toString(): String {
            return hotels.toString()
        }
}