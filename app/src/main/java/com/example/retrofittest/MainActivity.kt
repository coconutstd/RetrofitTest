package com.example.retrofittest

import CustomAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter = CustomAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://4cxysyupk7.execute-api.ap-northeast-2.amazonaws.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        buttonRequest.setOnClickListener {
            val sensorService = retrofit.create(SensorService::class.java)
            sensorService.sensors().enqueue(object : Callback<List<SensorItem>> {
                override fun onResponse(call: Call<List<SensorItem>>, response: Response<List<SensorItem>>) {
                    Log.i("hello", "success")
                    adapter.sensorList.addAll(response.body() as List<SensorItem>)
                    adapter.notifyDataSetChanged()
                }
                override fun onFailure(call: Call<List<SensorItem>>, t: Throwable) {
                    Log.d("hello","실패 : {$t}")
                }
            })
        }
    }
}