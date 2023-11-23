package com.example.gch.api

import com.example.gch.Models.Certif
import com.example.gch.Models.Formation
import com.example.gch.Models.Participation
import com.example.gch.Models.Question
import com.example.gch.Models.Quiz
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface FormationApi {
    @GET("getAllformation")
    fun getFormationsList():Call<List<Formation>>

    @GET("getAllformationbyName")
    fun getEntrepFormations(@Query("addedBy") addedBy: String): Call<List<Formation>>


    @GET("getAllcertif")
    fun getCertifList():Call<List<Certif>>

    @GET("getAllquizes")
    fun getQuizList():Call<List<Quiz>>

    @GET("getcertif")
    fun getCertifByTitle(@Query("title") title: String): Call<Certif>

    @POST("addFormation")
    fun addFormation(@Body formation: Formation): Call<Formation>

    @DELETE("deleteFormation")
    fun deleteFormation(@Query("title") title: String): Call<Void>


    @POST("addparticipation")
    fun addParticipation(@Body participation: Participation): Call<Void>

    @GET("getparticipation")
    fun getParticipation(
        @Query("userId") userId: String,
        @Query("quizId") quizId: String
    ): Call<Participation>






}

