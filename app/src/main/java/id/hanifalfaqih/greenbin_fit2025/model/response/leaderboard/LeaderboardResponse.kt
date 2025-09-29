package id.hanifalfaqih.greenbin_fit2025.model.response.leaderboard

data class LeaderboardResponse(
    val code: String,
    val status: String,
    val message: String,
    val data: List<LeaderboardItem>
)

data class LeaderboardItem(
    val name: String,
    val total_points: String,
)
