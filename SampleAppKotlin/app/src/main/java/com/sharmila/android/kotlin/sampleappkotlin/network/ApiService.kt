package com.sharmila.android.kotlin.sampleappkotlin.network

import com.sharmila.android.kotlin.sampleappkotlin.model.HotelList
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("hotels")
    fun getHotelList() : Call<HotelList>

}