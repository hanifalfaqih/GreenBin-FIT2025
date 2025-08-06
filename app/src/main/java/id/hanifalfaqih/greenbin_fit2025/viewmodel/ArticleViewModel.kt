package id.hanifalfaqih.greenbin_fit2025.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.hanifalfaqih.greenbin_fit2025.model.response.article.ArticleDetail
import id.hanifalfaqih.greenbin_fit2025.model.response.article.ArticleItem
import id.hanifalfaqih.greenbin_fit2025.network.client.RetrofitInstance
import id.hanifalfaqih.greenbin_fit2025.network.service.ArticleService
import id.hanifalfaqih.greenbin_fit2025.repository.ArticleRepository
import kotlinx.coroutines.launch

class ArticleViewModel: ViewModel() {

    private val articleService = RetrofitInstance.getInstance().create(ArticleService::class.java)
    private val repository by lazy {
        ArticleRepository(articleService)
    }

    val articleList = MutableLiveData<List<ArticleItem>>()
    val articleDetail = MutableLiveData<ArticleDetail>()
    val errorMessage = MutableLiveData<String>()

    fun getTop5Articles() {
        viewModelScope.launch {
            try {
                articleList.value = repository.getTop5Article()
            } catch (e: Exception) {
                errorMessage.value = e.message.toString()
            }
        }
    }

    fun getAllArticle() {
        viewModelScope.launch {
            try {
                articleList.value = repository.getAllArticle()
            } catch (e: Exception) {
                errorMessage.value = e.message.toString()
            }
        }
    }

    fun getDetailArticle(articleId: Int) {
        viewModelScope.launch {
            try {
                articleDetail.value = repository.getDetailArticle(articleId).data
            } catch (e: Exception) {
                errorMessage.value = e.message.toString()
            }
        }
    }

}