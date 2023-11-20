package tn.esprit.gestionuser.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tn.esprit.gestionuser.model.ApiError
import tn.esprit.gestionuser.model.ApiResponse
import tn.esprit.gestionuser.model.LoginRequest
import tn.esprit.gestionuser.model.User
import tn.esprit.gestionuser.network.RetrofitInstance

class LoginViewModel : ViewModel() {

    private val apiService = RetrofitInstance.RetrofitClient.instance

    private val _loginResult = MutableLiveData<ApiResponse<User>>()
    val loginResult: LiveData<ApiResponse<User>> get() = _loginResult

    fun login(email: String, password: String) {
        val loginRequest = LoginRequest(email, password)

        apiService.getUserByEmail(email).enqueue(object : Callback<ApiResponse<User>> {

            override fun onResponse(call: Call<ApiResponse<User>>, response: Response<ApiResponse<User>>) {
                if (response.isSuccessful) {
                    _loginResult.value = response.body()
                } else {
                    _loginResult.value = ApiResponse(status = "error", error = ApiError(response.code(), response.message()))
                }
            }

            override fun onFailure(call: Call<ApiResponse<User>>, t: Throwable) {
                _loginResult.value = ApiResponse(status = "error", error = ApiError(0, t.localizedMessage))
            }
        })
    }
}
