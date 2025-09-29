package id.hanifalfaqih.greenbin_fit2025.ui.leaderboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import id.hanifalfaqih.greenbin_fit2025.adapter.ListLeaderboardAdapter
import id.hanifalfaqih.greenbin_fit2025.databinding.ActivityLeaderboardBinding
import id.hanifalfaqih.greenbin_fit2025.model.response.leaderboard.LeaderboardItem

class LeaderboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLeaderboardBinding
    private lateinit var leaderboardAdapter: ListLeaderboardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLeaderboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
}
