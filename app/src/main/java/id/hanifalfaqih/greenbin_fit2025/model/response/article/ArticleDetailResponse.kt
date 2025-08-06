package id.hanifalfaqih.greenbin_fit2025.model.response.article

data class ArticleDetailResponse(
    val code: String,
    val status: String,
    val message: String,
    val data: ArticleDetail
)

data class ArticleDetail(
    val id: String,
    val title: String,
    val image: String,
    val content: String,
    val views: Int
)