package com.example.gch.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.gch.api.FormationApi

object RetrofitClient {
    private const val ip = "192.168.1.168";
    private const val BASE_URL = "http://"+ ip +":3000/"

    val instance: FormationApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(FormationApi::class.java)
    }
}