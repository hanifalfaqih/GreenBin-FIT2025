package id.hanifalfaqih.greenbin_fit2025.ui.article

import android.content.Intent
import android.os.Bundle
import android.provider.Telephony.Mms.Part.CONTENT_ID
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import id.hanifalfaqih.greenbin_fit2025.MainMenuActivity
import id.hanifalfaqih.greenbin_fit2025.R
import id.hanifalfaqih.greenbin_fit2025.adapter.ListArticleAdapter
import id.hanifalfaqih.greenbin_fit2025.databinding.ActivityArticleBinding
import id.hanifalfaqih.greenbin_fit2025.model.response.article.ArticleDetail
import id.hanifalfaqih.greenbin_fit2025.viewmodel.ArticleViewModel

class ArticleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArticleBinding
    private lateinit var articleViewModel: ArticleViewModel
    private lateinit var articleAdapter: ListArticleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**
         * Get data from view model
         */
        articleViewModel.getAllArticle()

        /**
         * Observer from get data above
         */
        articleViewModel.articleList.observe(this) { historyList ->
            articleAdapter.submitList(historyList)
        }

        /**
         * Create instance adapter, set adapter and layout manager
         */
        articleAdapter = ListArticleAdapter { articleId ->
            intentToDetailArticle(articleId)
        }
        binding.articleRv.adapter = articleAdapter
        binding.articleRv.layoutManager = LinearLayoutManager(this)


        binding.backButton.setOnClickListener {
            val intent = Intent(this, MainMenuActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun intentToDetailArticle(articleId: Int) {
        val intent = Intent(this, ArticleDetailActivity::class.java)
        intent.putExtra("ARTICLE_ID", articleId)
        startActivity(intent)
    }
}