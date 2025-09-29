package id.hanifalfaqih.greenbin_fit2025.model.response.user

data class BadgeResponse(
    val code: String,
    val status: String,
    val message: String,
    val data: BadgeData
)

data class BadgeData(
    val badge: String
)