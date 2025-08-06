package id.hanifalfaqih.greenbin_fit2025.repository

import id.hanifalfaqih.greenbin_fit2025.model.response.user.PointResponse
import id.hanifalfaqih.greenbin_fit2025.model.response.user.ProfileResponse
import id.hanifalfaqih.greenbin_fit2025.network.service.UserService
import id.hanifalfaqih.greenbin_fit2025.util.TokenManager
import kotlinx.coroutines.flow.first

class UserRepository(
    private val userService: UserService,
    private val tokenManager: TokenManager
) {

    suspend fun getProfile(): ProfileResponse {
        val token = tokenManager.tokenFlow.first() ?: throw Exception("Token not found")
        return userService.getProfile("Bearer $token")
    }

    suspend fun getPoint(): PointResponse {
        val token = tokenManager.tokenFlow.first() ?: throw Exception("Token not found")
        return userService.getPoint("Bearer $token")
    }
}