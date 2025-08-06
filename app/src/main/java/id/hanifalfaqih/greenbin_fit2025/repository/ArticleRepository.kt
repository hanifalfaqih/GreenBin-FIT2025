package id.hanifalfaqih.greenbin_fit2025.repository

import id.hanifalfaqih.greenbin_fit2025.model.response.article.ArticleDetailResponse
import id.hanifalfaqih.greenbin_fit2025.model.response.article.ArticleItem
import id.hanifalfaqih.greenbin_fit2025.network.service.ArticleService

class ArticleRepository(
    private val articleService: ArticleService
) {

    suspend fun getTop5Article(): List<ArticleItem> {
        return articleService.getTop5Articles().data
    }

    suspend fun getAllArticle(): List<ArticleItem> {
        return articleService.getAllArticles().data
    }

    suspend fun getDetailArticle(articleId: Int): ArticleDetailResponse {
        return articleService.getDetailArticles(articleId)
    }

}