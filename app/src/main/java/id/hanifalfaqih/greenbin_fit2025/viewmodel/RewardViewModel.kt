package id.hanifalfaqih.greenbin_fit2025.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.hanifalfaqih.greenbin_fit2025.model.response.reward.RewardDetail
import id.hanifalfaqih.greenbin_fit2025.model.response.reward.RewardItem
import id.hanifalfaqih.greenbin_fit2025.network.client.RetrofitInstance
import id.hanifalfaqih.greenbin_fit2025.network.service.RewardService
import id.hanifalfaqih.greenbin_fit2025.repository.RewardRepository
import id.hanifalfaqih.greenbin_fit2025.util.TokenManager
import kotlinx.coroutines.launch

class RewardViewModel(private val tokenManager: TokenManager) : ViewModel() {

    private val rewardService = RetrofitInstance.getInstance().create(RewardService::class.java)

    private val repository by lazy {
        RewardRepository(rewardService, tokenManager)
    }

    val rewardList = MutableLiveData<List<RewardItem>>()
    val errorMessage = MutableLiveData<String>()
    val rewardDetail = MutableLiveData<RewardDetail>()

    fun getAllRewards() {
        viewModelScope.launch {
            try {
                rewardList.value = repository.getAllRewards()
            } catch (e: Exception) {
                errorMessage.value = e.message.toString()
            }
        }
    }

    fun getDetailReward(rewardId: Int) {
        viewModelScope.launch {
            try {
                rewardDetail.value = repository.getRewardDetail(rewardId).data
            } catch (e: Exception) {
                errorMessage.value = e.message.toString()
            }
        }
    }

}