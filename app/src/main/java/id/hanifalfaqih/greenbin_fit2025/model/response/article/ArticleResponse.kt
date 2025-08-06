package id.hanifalfaqih.greenbin_fit2025.model.response.article

data class ArticleResponse(
    val code: String,
    val status: String,
    val message: String,
    val data: List<ArticleItem>
)
class ArticleItem(
    val id: String,
    val title: String,
    val image: String,
)
