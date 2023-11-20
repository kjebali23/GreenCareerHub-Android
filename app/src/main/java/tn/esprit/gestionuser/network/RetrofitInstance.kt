package tn.esprit.gestionuser.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tn.esprit.gestionuser.respository.ApiService


object RetrofitInstance {


    object RetrofitClient {
        private const val BASE_URL = "http://192.168.202.74:3000/"

        val instance: ApiService by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofit.create(ApiService::class.java)
        }
    }
}