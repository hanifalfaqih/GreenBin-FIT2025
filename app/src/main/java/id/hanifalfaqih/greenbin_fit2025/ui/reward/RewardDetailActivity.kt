package id.hanifalfaqih.greenbin_fit2025.ui.reward

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import id.hanifalfaqih.greenbin_fit2025.R
import id.hanifalfaqih.greenbin_fit2025.databinding.ActivityRewardDetailBinding
import id.hanifalfaqih.greenbin_fit2025.model.response.reward.RewardDetail
import id.hanifalfaqih.greenbin_fit2025.viewmodel.RewardViewModel
import id.hanifalfaqih.greenbin_fit2025.viewmodel.UserViewModel

class RewardDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRewardDetailBinding
    private lateinit var rewardViewModel: RewardViewModel
    private lateinit var userViewModel: UserViewModel

    private var content: RewardDetail? = null
    private var rewardId = 0
    private var pointUser = 0
    private var pointReward = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRewardDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userViewModel.getPoint()

        rewardId = intent.getIntExtra("REWARD_ID", 0)
        rewardId.let {
            rewardViewModel.getDetailReward(it)
        }

        rewardViewModel.rewardDetail.observe(this) { rewardDetail ->
            setContentData(rewardDetail)
        }

        userViewModel.point.observe(this) { point ->
            this.pointUser = point
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

    }
}