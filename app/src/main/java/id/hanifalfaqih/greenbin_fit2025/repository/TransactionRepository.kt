package id.hanifalfaqih.greenbin_fit2025.repository

import id.hanifalfaqih.greenbin_fit2025.model.request.TransactionRequest
import id.hanifalfaqih.greenbin_fit2025.model.response.transaction.TransactionHistoryItem
import id.hanifalfaqih.greenbin_fit2025.model.response.transaction.TransactionHistoryResponse
import id.hanifalfaqih.greenbin_fit2025.model.response.transaction.TransactionResponse
import id.hanifalfaqih.greenbin_fit2025.network.service.TransactionService
import id.hanifalfaqih.greenbin_fit2025.util.TokenManager
import kotlinx.coroutines.flow.first

class TransactionRepository(
    private val transactionService: TransactionService,
    private val tokenManager: TokenManager
) {

    suspend fun createTransaction(transactionRequest: TransactionRequest): TransactionResponse {
        val token = tokenManager.tokenFlow.first() ?: throw Exception("Token not found")
        return transactionService.createTransaction("Bearer $token", transactionRequest)
    }

    suspend fun getTransactionHistory(): TransactionHistoryResponse {
        val token = tokenManager.tokenFlow.first() ?: throw Exception("Token not found")
        return transactionService.transactionHistory("Bearer $token")
    }

}