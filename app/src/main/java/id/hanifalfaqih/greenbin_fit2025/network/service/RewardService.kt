package id.hanifalfaqih.greenbin_fit2025.network.service

import id.hanifalfaqih.greenbin_fit2025.model.response.reward.RewardDetailResponse
import id.hanifalfaqih.greenbin_fit2025.model.response.reward.RewardItem
import id.hanifalfaqih.greenbin_fit2025.model.response.reward.RewardResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface RewardService {

    @GET("/api/v1/reward/get-all")
    suspend fun getAllRewards(): RewardResponse

    @GET("/api/v1/reward/detail/{id}")
    suspend fun getDetailReward(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): RewardDetailResponse


}