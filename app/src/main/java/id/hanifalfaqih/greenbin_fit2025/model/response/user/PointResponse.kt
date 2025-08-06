package id.hanifalfaqih.greenbin_fit2025.model.response.user

data class PointResponse(
    val code: String,
    val status: String,
    val message: String,
    val data: PointData
)

data class PointData(
    val point: Int
)