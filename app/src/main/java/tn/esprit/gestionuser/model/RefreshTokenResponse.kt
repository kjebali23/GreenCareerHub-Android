package tn.esprit.gestionuser.model

data class RefreshTokenResponse(
    val accessToken: String,
    val refreshToken: String
)
