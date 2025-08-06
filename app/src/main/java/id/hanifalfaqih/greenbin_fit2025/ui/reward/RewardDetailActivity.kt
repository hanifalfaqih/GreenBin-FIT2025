package id.hanifalfaqih.greenbin_fit2025.ui.reward

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import id.hanifalfaqih.greenbin_fit2025.MainMenuActivity
import id.hanifalfaqih.greenbin_fit2025.R
import id.hanifalfaqih.greenbin_fit2025.databinding.ActivityRewardDetailBinding
import id.hanifalfaqih.greenbin_fit2025.model.response.reward.RewardDetail
import id.hanifalfaqih.greenbin_fit2025.util.TokenManager
import id.hanifalfaqih.greenbin_fit2025.viewmodel.RewardViewModel
import id.hanifalfaqih.greenbin_fit2025.viewmodel.TransactionViewModel
import id.hanifalfaqih.greenbin_fit2025.viewmodel.UserViewModel

class RewardDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRewardDetailBinding
    private lateinit var rewardViewModel: RewardViewModel
    private lateinit var userViewModel: UserViewModel
    private lateinit var transactionViewModel: TransactionViewModel

    private var content: RewardDetail? = null
    private var rewardId = 0
    private var pointUser = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRewardDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tokenManager = TokenManager(applicationContext)

        rewardViewModel = RewardViewModel(tokenManager)
        userViewModel = UserViewModel(tokenManager)
        transactionViewModel = TransactionViewModel(tokenManager)

        userViewModel.getPoint()

        rewardId = intent.getIntExtra("REWARD_ID", 0)
        rewardId.let {
            rewardViewModel.getDetailReward(it)
        }

        rewardViewModel.rewardDetail.observe(this) { rewardDetail ->
            setContentData(rewardDetail)
        }

        userViewModel.points.observe(this) { point ->
            this.pointUser = point
        }

        binding.redeemButton.setOnClickListener {
            transactionViewModel.redeemReward(rewardId)
        }

        binding.backButton.setOnClickListener {
            val intent = Intent(this, MainMenuActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun setContentData(rewardDetail: RewardDetail) {
        content.apply {
            Glide.with(this@RewardDetailActivity)
                .load(rewardDetail.image)
                .into(binding.rewardImage)

            binding.rewardTitle.text = rewardDetail.title
            binding.rewardPoint.text = rewardDetail.point.toString()
            binding.rewardDescription.text = rewardDetail.description
        }

        val pointBalance = pointUser - rewardDetail.point
        binding.pointText.text = pointBalance.toString()

        if (pointUser < rewardDetail.point) {
            binding.balanceMessage.text = "Poin anda tidak cukup"
            binding.pointText.isVisible = false
            binding.redeemButton.isVisible = false
        }

    }
}