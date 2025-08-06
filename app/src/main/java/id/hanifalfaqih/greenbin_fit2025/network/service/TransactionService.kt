package id.hanifalfaqih.greenbin_fit2025.network.service

import id.hanifalfaqih.greenbin_fit2025.model.request.RedeemRequest
import id.hanifalfaqih.greenbin_fit2025.model.request.TransactionRequest
import id.hanifalfaqih.greenbin_fit2025.model.response.transaction.TransactionHistoryResponse
import id.hanifalfaqih.greenbin_fit2025.model.response.transaction.TransactionResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface TransactionService {

    @POST("/api/v1/transaction/create")
    suspend fun createTransaction(
        @Header("Authorization") token: String,
        @Body transactionRequest: TransactionRequest
    ): TransactionResponse

    @POST("/api/v1/transaction/create")
    suspend fun redeemTransaction(
        @Header("Authorization") token: String,
        @Body redeemRequest: RedeemRequest
    ): TransactionResponse

    @GET("/api/v1/transaction/history")
    suspend fun transactionHistory(
        @Header("Authorization") token: String
    ): TransactionHistoryResponse

}