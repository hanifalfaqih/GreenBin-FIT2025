package id.hanifalfaqih.greenbin_fit2025.model.response.reward

data class RewardResponse(
    val code: String,
    val status: String,
    val message: String,
    val data: List<RewardItem>
)

data class RewardItem(
    val id: String,
    val title: String,
    val image: String,
    val point: Int
)