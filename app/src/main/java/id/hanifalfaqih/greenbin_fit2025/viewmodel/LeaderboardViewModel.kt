package id.hanifalfaqih.greenbin_fit2025.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.hanifalfaqih.greenbin_fit2025.model.response.leaderboard.LeaderboardItem
import id.hanifalfaqih.greenbin_fit2025.model.response.transaction.TransactionHistoryItem
import id.hanifalfaqih.greenbin_fit2025.network.client.RetrofitInstance
import id.hanifalfaqih.greenbin_fit2025.network.service.LeaderboardService
import id.hanifalfaqih.greenbin_fit2025.network.service.TransactionService
import id.hanifalfaqih.greenbin_fit2025.repository.LeaderboardRepository
import id.hanifalfaqih.greenbin_fit2025.repository.TransactionRepository
import id.hanifalfaqih.greenbin_fit2025.util.TokenManager
import kotlinx.coroutines.launch

class LeaderboardViewModel (private val tokenManager: TokenManager) : ViewModel() {

    private val leaderboardService =
        RetrofitInstance.getInstance().create(LeaderboardService::class.java)

    private val repository by lazy {
        LeaderboardRepository(leaderboardService, tokenManager)
    }

    val leaderboardList = MutableLiveData<List<LeaderboardItem>>()
    val errorMessage = MutableLiveData<String>()

    fun getLeaderboard() {
        viewModelScope.launch {
            try {
                leaderboardList.value = repository.getLeaderboard().data
            } catch (e: Exception) {
                errorMessage.value = e.message.toString()
            }
        }
    }
}