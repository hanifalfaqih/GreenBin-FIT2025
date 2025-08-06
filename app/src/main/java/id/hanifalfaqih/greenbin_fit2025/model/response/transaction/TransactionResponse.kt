package id.hanifalfaqih.greenbin_fit2025.model.response.transaction

data class TransactionResponse(
    val code: String,
    val status: String,
    val message: String,
    val data: TransactionData
)

data class TransactionData(
    val id: String,
    val transaction_id: String,
    val user_id: Int,
    val status_point: String,
    val point: Int
)