package id.hanifalfaqih.greenbin_fit2025.network.service

import id.hanifalfaqih.greenbin_fit2025.model.request.LoginRequest
import id.hanifalfaqih.greenbin_fit2025.model.request.RegisterRequest
import id.hanifalfaqih.greenbin_fit2025.model.response.auth.LoginResponse
import id.hanifalfaqih.greenbin_fit2025.model.response.auth.LogoutResponse
import id.hanifalfaqih.greenbin_fit2025.model.response.auth.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {

    @POST("/api/v1/auth/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): LoginResponse

    @POST("/api/v1/auth/register")
    suspend fun register(
        @Body registerRequest: RegisterRequest
    ): RegisterResponse

    @POST("/api/v1/auth/logout")
    suspend fun logout(
        @Header("Authorization") authorization: String
    ): LogoutResponse
}