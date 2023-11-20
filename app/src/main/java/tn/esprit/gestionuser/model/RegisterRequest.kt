package tn.esprit.gestionuser.model

data class RegisterRequest(
    val email: String,
    val password: String,
    val phoneNumber: String?,
    val cv: String?,
    val birthDate: String?,
    val profilePicture: String?
)
