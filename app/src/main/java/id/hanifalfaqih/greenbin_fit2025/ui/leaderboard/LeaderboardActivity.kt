package id.hanifalfaqih.greenbin_fit2025.ui.leaderboard

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import id.hanifalfaqih.greenbin_fit2025.MainMenuActivity
import id.hanifalfaqih.greenbin_fit2025.adapter.ListLeaderboardAdapter
import id.hanifalfaqih.greenbin_fit2025.databinding.ActivityLeaderboardBinding
import id.hanifalfaqih.greenbin_fit2025.model.response.leaderboard.LeaderboardItem
import id.hanifalfaqih.greenbin_fit2025.util.TokenManager
import id.hanifalfaqih.greenbin_fit2025.viewmodel.LeaderboardViewModel
import id.hanifalfaqih.greenbin_fit2025.viewmodel.TransactionViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.concurrent.TimeUnit

class LeaderboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLeaderboardBinding
    private lateinit var leaderBoardViewModel: LeaderboardViewModel
    private lateinit var leaderboardAdapter: ListLeaderboardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLeaderboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.timeRemaining.text = getRemainingTime()
        startRemainingTimeUpdater()

        val tokenManager = TokenManager(applicationContext)
        leaderBoardViewModel = LeaderboardViewModel(tokenManager)

        leaderboardAdapter = ListLeaderboardAdapter()
        binding.leaderboardRv.adapter = leaderboardAdapter
        binding.leaderboardRv.layoutManager = LinearLayoutManager(this)

        leaderBoardViewModel.getLeaderboard()
        leaderBoardViewModel.leaderboardList.observe(this) { leaderboardList ->
            if (leaderboardList.isNotEmpty()) {
                val top3 = leaderboardList.take(3)
                val others = if (leaderboardList.size > 3) leaderboardList.drop(3) else emptyList()

                top3.getOrNull(0)?.let {
                    binding.tvName1.text = it.name
                    binding.tvPoints1.text = "${it.total_points} pts"
                }

                top3.getOrNull(1)?.let {
                    binding.tvName2.text = it.name
                    binding.tvPoints2.text = "${it.total_points} pts"
                }

                top3.getOrNull(2)?.let {
                    binding.tvName3.text = it.name
                    binding.tvPoints3.text = "${it.total_points} pts"
                }

                leaderboardAdapter.submitList(others)
            }
        }

        binding.backButton.setOnClickListener {
            val intent = Intent(this, MainMenuActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun startRemainingTimeUpdater() {
        lifecycleScope.launch {
            while (isActive) {
                binding.timeRemaining.text = getRemainingTime()
                delay(60 * 1000) // Ini update tiap 1 menit
            }
        }
    }

    private fun getRemainingTime(): String {
        val now = Calendar.getInstance()

        val endOfMonth = Calendar.getInstance().apply {
            set(Calendar.DAY_OF_MONTH, getActualMaximum(Calendar.DAY_OF_MONTH))
            set(Calendar.HOUR_OF_DAY, 23)
            set(Calendar.MINUTE, 59)
            set(Calendar.SECOND, 59)
        }

        val diffInMillis = endOfMonth.timeInMillis - now.timeInMillis

        val days = TimeUnit.MILLISECONDS.toDays(diffInMillis)
        val hours = TimeUnit.MILLISECONDS.toHours(diffInMillis) % 24
        val minutes = TimeUnit.MILLISECONDS.toMinutes(diffInMillis) % 60

        return "Sisa waktu: $days hari $hours jam $minutes menit"
    }
}
