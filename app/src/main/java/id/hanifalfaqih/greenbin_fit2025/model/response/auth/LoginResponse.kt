package id.hanifalfaqih.greenbin_fit2025.model.response.auth

data class LoginResponse(
    val code: String,
    val status: String,
    val message: String,
    val data: LoginData
)

data class LoginData(
    val access_token: String,
    val token_type: String
)