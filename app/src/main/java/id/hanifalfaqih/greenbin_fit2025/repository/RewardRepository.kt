package id.hanifalfaqih.greenbin_fit2025.repository

import id.hanifalfaqih.greenbin_fit2025.model.response.reward.RewardDetailResponse
import id.hanifalfaqih.greenbin_fit2025.model.response.reward.RewardItem
import id.hanifalfaqih.greenbin_fit2025.network.service.RewardService
import id.hanifalfaqih.greenbin_fit2025.util.TokenManager
import kotlinx.coroutines.flow.first

class RewardRepository(
    private val rewardService: RewardService,
    private val tokenManager: TokenManager
) {

    suspend fun getAllRewards(): List<RewardItem> {
        return rewardService.getAllRewards()
    }

    suspend fun getRewardDetail(rewardId: Int): RewardDetailResponse {
        val token = tokenManager.tokenFlow.first() ?: throw Exception("Token not found")
        return rewardService.getDetailReward(token = "Bearer $token", id = rewardId)
    }

}