package com.example.gch.api

import com.example.gch.Models.Certif
import com.example.gch.Models.Formation
import retrofit2.http.GET
import retrofit2.Call
interface FormationApi {
    @GET("getAllformation")
    fun getFormationsList():Call<List<Formation>>

    @GET("getAllcertif")
    fun getCertifList():Call<List<Certif>>
}