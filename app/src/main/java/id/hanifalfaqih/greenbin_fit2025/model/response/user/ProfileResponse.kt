package id.hanifalfaqih.greenbin_fit2025.model.response.user

data class ProfileResponse(
    val code: String,
    val status: String,
    val message: String,
    val data: ProfileData
)

data class ProfileData(
    val id: String,
    val name: String,
    val email: String
)