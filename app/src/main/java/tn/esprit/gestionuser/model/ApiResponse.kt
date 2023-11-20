package tn.esprit.gestionuser.model

data class ApiResponse<T> (
    val status: String,
    val message: String? = null,
    val data: T? = null,
    val error: ApiError? = null
)