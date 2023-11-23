package tn.esprit.gestionevenement

import Hack
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH  // Ajout de PATCH pour la mise à jour
import retrofit2.http.POST
import retrofit2.http.Path

interface HackApi {
    @GET("ha")
    fun getHackList(): Call<List<Hack>>

    @POST("hackathon")
    fun addHackathon(@Body hack: Hack): Call<Hack>

    @PATCH("{hackathonId}")  // Utilisation de PATCH pour la mise à jour
    fun updateHackathon(@Path("hackathonId") hackId: String, @Body hack: Hack): Call<Hack>

    @DELETE("{hackathonId}")
    fun deleteTodo(@Path("hackathonId") hackId: String): Call<Void>
}
