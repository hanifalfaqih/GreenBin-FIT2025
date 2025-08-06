package id.hanifalfaqih.greenbin_fit2025.model.response.reward

data class RewardDetailResponse(
    val code: String,
    val status: String,
    val message: String,
    val data: RewardDetail
)

data class RewardDetail(
    val id: String,
    val title: String,
    val image: String,
    val description: String,
    val point: Int
)