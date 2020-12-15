package com.example.retrofittest

import com.example.retrofittest.SensorItem
import retrofit2.Call
import retrofit2.http.GET

interface SensorService {
    @GET("dev/sensor")
    fun sensors(): Call<List<SensorItem>>
}