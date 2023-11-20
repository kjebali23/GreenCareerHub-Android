package tn.esprit.gestionuser.respository

import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*
import tn.esprit.gestionuser.model.*

interface ApiService {
    @GET("/")
    fun getHomepage(): Call<ApiResponse<Any>> // Change Any to the expected type if applicable

    @POST("auth/login")
    fun login(@Body loginRequest: LoginRequest): Call<ApiResponse<User>>

    @POST("auth/register")
    fun register(@Body registerRequest: RegisterRequest): Call<ApiResponse<User>>

    @POST("auth/refresh-token")
    fun refreshToken(@Body refreshTokenRequest: RefreshTokenRequest): Call<ApiResponse<RefreshTokenResponse>>

    @DELETE("auth/logout")
    fun logout(@Body logoutRequest: LogoutRequest): Call<ApiResponse<Any>>

    @GET("users/{email}")
    fun getUserByEmail(@Path("email") email: String): Call<ApiResponse<User>>
}
