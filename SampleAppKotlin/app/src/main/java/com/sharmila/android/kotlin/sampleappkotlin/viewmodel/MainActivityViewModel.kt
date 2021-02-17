package com.sharmila.android.kotlin.sampleappkotlin.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sharmila.android.kotlin.sampleappkotlin.model.HotelList
import com.sharmila.android.kotlin.sampleappkotlin.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivityViewModel : ViewModel() {

    //var hotelList: MutableLiveData<HotelList>=MutableLiveData()
    lateinit var hotelsList:MutableLiveData<HotelList>

    //we will call this method to get the data
    fun getHotels(): LiveData<HotelList>{
        hotelsList=MutableLiveData<HotelList>()
        loadHotels()
        return hotelsList
    }

    private fun loadHotels() {

        //This method is using Retrofit to get the JSON data from URL
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://dl.dropboxusercontent.com/s/6nt7fkdt7ck0lue/hotels.json/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ApiService::class.java)
        val call: Call<HotelList> = service.getHotelList()
        call.enqueue(object : Callback<HotelList?> {

            override fun onResponse(call: Call<HotelList?>, response: Response<HotelList?>) {
                //finally we are setting the list to our MutableLiveData
                hotelsList.value=response.body()

            }

            override fun onFailure(call: Call<HotelList?>, t: Throwable) {
                Log.d("******************", t.message.toString())
            }
        })
    }

}