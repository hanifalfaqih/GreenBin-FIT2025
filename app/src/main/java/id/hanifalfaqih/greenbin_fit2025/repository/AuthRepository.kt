package id.hanifalfaqih.greenbin_fit2025.repository

import android.util.Log
import id.hanifalfaqih.greenbin_fit2025.model.request.LoginRequest
import id.hanifalfaqih.greenbin_fit2025.model.request.RegisterRequest
import id.hanifalfaqih.greenbin_fit2025.model.response.auth.LoginResponse
import id.hanifalfaqih.greenbin_fit2025.model.response.auth.LogoutResponse
import id.hanifalfaqih.greenbin_fit2025.model.response.auth.RegisterResponse
import id.hanifalfaqih.greenbin_fit2025.network.service.AuthService
import id.hanifalfaqih.greenbin_fit2025.util.TokenManager

class AuthRepository(
    private val authService: AuthService,
    private val tokenManager: TokenManager
) {

    suspend fun login(email: String, password: String): LoginResponse {
        val loginRequest = LoginRequest(email, password)
        return authService.login(loginRequest)
    }

    suspend fun register(name: String, email: String, password: String): RegisterResponse {
        val registerRequest = RegisterRequest(name, email, password)
        return authService.register(registerRequest)
    }

    suspend fun logout(): LogoutResponse {
        val token = tokenManager.tokenFlow.collect { it }
        Log.d("AuthRepository", token.toString())
        return authService.logout("Bearer $token")
    }

}