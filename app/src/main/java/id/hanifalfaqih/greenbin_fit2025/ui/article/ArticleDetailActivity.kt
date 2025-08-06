package id.hanifalfaqih.greenbin_fit2025.ui.article

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import id.hanifalfaqih.greenbin_fit2025.databinding.ActivityArticleDetailBinding
import id.hanifalfaqih.greenbin_fit2025.model.response.article.ArticleDetail
import id.hanifalfaqih.greenbin_fit2025.viewmodel.ArticleViewModel

class ArticleDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArticleDetailBinding
    private lateinit var articleViewModel: ArticleViewModel

    private var content: ArticleDetail? = null
    private var articleId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        articleViewModel = ArticleViewModel()

        articleId = intent.getIntExtra("ARTICLE_ID", 0)
        articleId.let {
            articleViewModel.getDetailArticle(it)
        }

        articleViewModel.articleDetail.observe(this) { articleDetail ->
            setContentData(articleDetail)
        }

    }

    private fun setContentData(articleDetail: ArticleDetail) {
        content.apply {
            Glide.with(this@ArticleDetailActivity)
                .load(articleDetail.image)
                .into(binding.ivThumbnail)

            binding.tvTitle.text = articleDetail.title
            binding.tvViewsContent.text = articleDetail.views.toString()
            binding.tvContent.text = articleDetail.content
        }
    }
}