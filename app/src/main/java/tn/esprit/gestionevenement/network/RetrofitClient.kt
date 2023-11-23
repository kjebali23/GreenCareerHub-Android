package tn.esprit.gestionevenement.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tn.esprit.gestionevenement.ConfApi
import tn.esprit.gestionevenement.HackApi

object RetrofitClient {
    private val ip = "192.168.1.17";
    private val BASE_URL = "http://"+ip+":8000/conf/"
    private val BASE_URL_hack = "http://"+ip+":8000/hack/"

    val instance: ConfApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ConfApi::class.java)
    }
    val instancehack: HackApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL_hack)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(HackApi::class.java)
    }
}
