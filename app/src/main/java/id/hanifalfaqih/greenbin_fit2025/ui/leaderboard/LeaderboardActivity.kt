package id.hanifalfaqih.greenbin_fit2025.ui.leaderboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
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

        val tokenManager = TokenManager(applicationContext)
        leaderBoardViewModel = LeaderboardViewModel(tokenManager)

        leaderBoardViewModel.getLeaderboard()

        binding.timeRemaining.text = getRemainingTime()
        startRemainingTimeUpdater()

        leaderboardAdapter = ListLeaderboardAdapter()
        binding.leaderboardRv.adapter = leaderboardAdapter
        binding.leaderboardRv.layoutManager = LinearLayoutManager(this)

        val dummyList = listOf(
            LeaderboardItem("User 4", "800"),
            LeaderboardItem("User 5", "750"),
            LeaderboardItem("User 6", "700"),
            LeaderboardItem("User 7", "650"),
        )
        leaderboardAdapter.submitList(dummyList)
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
