package id.hanifalfaqih.greenbin_fit2025.repository

import id.hanifalfaqih.greenbin_fit2025.model.response.leaderboard.LeaderboardResponse
import id.hanifalfaqih.greenbin_fit2025.network.service.LeaderboardService
import id.hanifalfaqih.greenbin_fit2025.util.TokenManager
import kotlinx.coroutines.flow.first

class LeaderboardRepository (
    private val leaderboardService: LeaderboardService,
    private val tokenManager: TokenManager
) {
    suspend fun getLeaderboard(): LeaderboardResponse {
        val token = tokenManager.tokenFlow.first() ?: throw Exception("Token not found")
        return leaderboardService.getLeaderboard("Bearer $token")
    }
}