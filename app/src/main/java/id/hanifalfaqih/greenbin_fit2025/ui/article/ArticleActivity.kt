package id.hanifalfaqih.greenbin_fit2025.ui.article

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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
        enableEdgeToEdge()

        binding = ActivityArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.backButton.setOnClickListener {
            val intent = Intent(this, MainMenuActivity::class.java)
            startActivity(intent)
            finish()
        }

        articleViewModel.getAllArticle()
        articleAdapter = ListArticleAdapter { articleId ->
            intentToDetailArticle(articleId)
        }

        articleViewModel.articleList.observe(this) { historyList ->
            articleAdapter.submitList(historyList)
        }
    }

    private fun intentToDetailArticle(articleId: Int) {
//        val intent = Intent(this, ArticleDetailActivity::class.java)
//        intent.putExtra(CONTENT_ID, id)
//        startActivity(intent)
    }
}