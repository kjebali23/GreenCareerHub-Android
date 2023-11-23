package tn.esprit.gestionevenement

import Conf
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH  // Ajout de PATCH pour la mise à jour
import retrofit2.http.POST
import retrofit2.http.Path

interface ConfApi {
    @GET("conferences")
    fun getConfList(): Call<List<Conf>>

    @POST("conference")
    fun addConference(@Body conf: Conf): Call<Conf>

    @PATCH("{conferenceId}")  // Utilisation de PATCH pour la mise à jour
    fun updateConference(@Path("conferenceId") confId: String, @Body conf: Conf): Call<Conf>

    @DELETE("{conferenceId}")
    fun deleteTodo(@Path("conferenceId") confId: String): Call<Void>
}
