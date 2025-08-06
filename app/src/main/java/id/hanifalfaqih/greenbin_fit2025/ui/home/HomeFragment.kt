package id.hanifalfaqih.greenbin_fit2025.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.hanifalfaqih.greenbin_fit2025.adapter.ListArticleAdapter
import id.hanifalfaqih.greenbin_fit2025.databinding.FragmentHomeBinding
import id.hanifalfaqih.greenbin_fit2025.ui.article.ArticleDetailActivity
import id.hanifalfaqih.greenbin_fit2025.ui.calculator_point.CalculatorPointActivity
import id.hanifalfaqih.greenbin_fit2025.ui.history.HistoryActivity
import id.hanifalfaqih.greenbin_fit2025.ui.reward.RewardActivity
import id.hanifalfaqih.greenbin_fit2025.util.TokenManager
import id.hanifalfaqih.greenbin_fit2025.viewmodel.ArticleViewModel
import id.hanifalfaqih.greenbin_fit2025.viewmodel.UserViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var userViewModel: UserViewModel
    private lateinit var articleViewModel: ArticleViewModel

    private lateinit var articleAdapter: ListArticleAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tokenManager = TokenManager(requireContext())
        userViewModel = UserViewModel(tokenManager)
        articleViewModel = ArticleViewModel()

        /**
         * Get data from view model
         */
        userViewModel.getProfile()
        userViewModel.getPoint()
        articleViewModel.getTop5Articles()

        /**
         * Observer from get data above
         */
        userViewModel.profileData.observe(viewLifecycleOwner) { profileData ->
            binding.usernameText.text = profileData.name + " 👋"
        }

        userViewModel.point.observe(viewLifecycleOwner) { point ->
            binding.pointText.text = point.toString()
        }

        articleViewModel.articleList.observe(viewLifecycleOwner) { articleList ->
            articleAdapter.submitList(articleList)
        }

        /**
         * Create instance adapter, set adapter and layout manager
         */
        articleAdapter = ListArticleAdapter { articleId ->
            intentToDetailArticle(articleId)
        }
        binding.articleRv.adapter = articleAdapter
        binding.articleRv.layoutManager = LinearLayoutManager(requireContext())


        binding.btnRedeemPoint.setOnClickListener {
            val intent = Intent(requireContext(), RewardActivity::class.java)
            startActivity(intent)
        }

        binding.btnCalculatorPoint.setOnClickListener {
            val intent = Intent(requireContext(), CalculatorPointActivity::class.java)
            startActivity(intent)
        }

        binding.btnHistory.setOnClickListener {
            val intent = Intent(requireContext(), HistoryActivity::class.java)
            startActivity(intent)
        }
    }

    private fun intentToDetailArticle(articleId: Int) {
        val intent = Intent(requireContext(), ArticleDetailActivity::class.java)
        intent.putExtra("ARTICLE_ID", articleId)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}