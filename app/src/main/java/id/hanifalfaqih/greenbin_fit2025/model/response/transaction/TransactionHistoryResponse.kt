package id.hanifalfaqih.greenbin_fit2025.model.response.transaction

import java.util.Date

data class TransactionHistoryResponse(
    val code: String,
    val status: String,
    val message: String,
    val data: List<TransactionHistoryItem>
)

data class TransactionHistoryItem(
    val id: Int,
    val transaction_id: String,
    val status_point: Int, // 0 = tambah dari mesin, 1 = tukar reward
    val weight: Double,
    val point: Int,
    val created_at: Date
)