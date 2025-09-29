package id.hanifalfaqih.greenbin_fit2025.network.service

import id.hanifalfaqih.greenbin_fit2025.model.response.leaderboard.LeaderboardResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface LeaderboardService {
    @GET("/api/v1/leaderboard")
    suspend fun getLeaderboard(
        @Header("Authorization") token: String
    ): LeaderboardResponse
}