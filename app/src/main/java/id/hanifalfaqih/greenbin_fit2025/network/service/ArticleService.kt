package id.hanifalfaqih.greenbin_fit2025.network.service

import id.hanifalfaqih.greenbin_fit2025.model.response.article.ArticleDetailResponse
import id.hanifalfaqih.greenbin_fit2025.model.response.article.ArticleResponse
import id.hanifalfaqih.greenbin_fit2025.model.response.reward.RewardDetailResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ArticleService {

    @GET("/api/v1/article/get-top-5")
    suspend fun getTop5Articles(): ArticleResponse

    @GET("/api/v1/article/get-all")
    suspend fun getAllArticles(): ArticleResponse

    @GET("/api/v1/article/detail/{id}")
    suspend fun getDetailArticles(
        @Path("id") id: Int
    ): ArticleDetailResponse

}