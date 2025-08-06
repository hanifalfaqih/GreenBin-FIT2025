package id.hanifalfaqih.greenbin_fit2025.model.response.auth

data class RegisterResponse(
    val code: String,
    val status: String,
    val message: String,
    val data: RegisterData
)

data class RegisterData(
    val id: String,
    val name: String,
    val email: String
)