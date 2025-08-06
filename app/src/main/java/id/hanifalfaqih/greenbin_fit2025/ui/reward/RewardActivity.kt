package id.hanifalfaqih.greenbin_fit2025.ui.reward

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import id.hanifalfaqih.greenbin_fit2025.R
import id.hanifalfaqih.greenbin_fit2025.adapter.ListRewardAdapter
import id.hanifalfaqih.greenbin_fit2025.databinding.ActivityRewardBinding
import id.hanifalfaqih.greenbin_fit2025.ui.article.ArticleDetailActivity
import id.hanifalfaqih.greenbin_fit2025.viewmodel.RewardViewModel

class RewardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRewardBinding
    private lateinit var rewardViewModel: RewardViewModel
    private lateinit var rewardAdapter: ListRewardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRewardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**
         * Get data from view model
         */
        rewardViewModel.getAllRewards()

        /**
         * Observer from get data above
         */
        rewardViewModel.rewardList.observe(this) { rewardList ->
            rewardAdapter.submitList(rewardList)
        }

        rewardAdapter = ListRewardAdapter { rewardId ->
            intentToDetailReward(rewardId)
        }
        binding.rewardRv.adapter = rewardAdapter
        binding.rewardRv.layoutManager = GridLayoutManager(this, 2)
    }

    private fun intentToDetailReward(rewardId: Int) {
        val intent = Intent(this, ArticleDetailActivity::class.java)
        intent.putExtra("REWARD_ID", rewardId)
        startActivity(intent)
    }
}