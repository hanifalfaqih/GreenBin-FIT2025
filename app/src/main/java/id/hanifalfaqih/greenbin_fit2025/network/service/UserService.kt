package id.hanifalfaqih.greenbin_fit2025.network.service

import id.hanifalfaqih.greenbin_fit2025.model.response.user.PointResponse
import id.hanifalfaqih.greenbin_fit2025.model.response.user.ProfileResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface UserService {

    @GET("/api/v1/profile")
    suspend fun getProfile(
        @Header("Authorization") token: String
    ): ProfileResponse

    @GET("/api/v1/profile/point")
    suspend fun getPoint(
        @Header("Authorization") token: String
    ): PointResponse

}