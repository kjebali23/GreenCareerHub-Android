package tn.esprit.gestionuser.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import tn.esprit.gestionuser.model.ApiError
import tn.esprit.gestionuser.model.ApiResponse
import tn.esprit.gestionuser.model.RegisterRequest
import tn.esprit.gestionuser.model.User
import tn.esprit.gestionuser.network.RetrofitInstance

class RegistrationViewModel: ViewModel() {
    private val apiService = RetrofitInstance.RetrofitClient.instance

    private val _registrationResult = MutableLiveData<ApiResponse<User>>()
    val registrationResult: LiveData<ApiResponse<User>> get() = _registrationResult

    fun register(email: String, password: String) {
        // Make the optional parameters null by default
        val registerRequest = RegisterRequest(email, password, null, null, null, null)

        apiService.register(registerRequest).enqueue(object : Callback<ApiResponse<User>> {
            override fun onResponse(call: Call<ApiResponse<User>>, response: Response<ApiResponse<User>>) {
                if (response.isSuccessful) {
                    _registrationResult.value = response.body()
                } else {
                    _registrationResult.value = ApiResponse(
                        status = "error",
                        error = ApiError(response.code(), response.message())
                    )
                }
            }

            override fun onFailure(call: Call<ApiResponse<User>>, t: Throwable) {
                _registrationResult.value = ApiResponse(status = "error", error = ApiError(0, t.localizedMessage))
            }
        })
    }
}