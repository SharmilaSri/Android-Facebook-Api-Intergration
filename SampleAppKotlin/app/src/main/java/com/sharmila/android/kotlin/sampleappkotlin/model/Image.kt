package com.sharmila.android.kotlin.sampleappkotlin.model

import android.provider.ContactsContract
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/* Created By Sharmila Prasath
 * February 2021
 * */
data class Image(
        @SerializedName("small")
        @Expose
        var small: String = "http://lorempixel.com/200/200/cats/7/",

        @SerializedName("medium")
        @Expose
        var medium: String = "http://lorempixel.com/200/200/cats/7/",

        @SerializedName("large")
        @Expose
        var large: String = "http://lorempixel.com/200/200/cats/7/"

){
    fun setSmall(small: String): Image {
        this.small = small
        return this
    }

    fun setLarge(large: String): Image {
        this.large = large
        return this
    }

    fun setMedium(medium: String):Image {
        this.medium = medium
        return this
    }
}