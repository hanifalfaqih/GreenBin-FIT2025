package id.hanifalfaqih.greenbin_fit2025.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.hanifalfaqih.greenbin_fit2025.model.request.RedeemRequest
import id.hanifalfaqih.greenbin_fit2025.model.request.TransactionRequest
import id.hanifalfaqih.greenbin_fit2025.model.response.transaction.TransactionData
import id.hanifalfaqih.greenbin_fit2025.model.response.transaction.TransactionHistoryItem
import id.hanifalfaqih.greenbin_fit2025.network.client.RetrofitInstance
import id.hanifalfaqih.greenbin_fit2025.network.service.TransactionService
import id.hanifalfaqih.greenbin_fit2025.repository.TransactionRepository
import id.hanifalfaqih.greenbin_fit2025.util.TokenManager
import kotlinx.coroutines.launch

class TransactionViewModel(private val tokenManager: TokenManager) : ViewModel() {

    private val transactionService =
        RetrofitInstance.getInstance().create(TransactionService::class.java)

    private val repository by lazy {
        TransactionRepository(transactionService, tokenManager)
    }

    val transactionHistoryList = MutableLiveData<List<TransactionHistoryItem>>()
    val errorMessage = MutableLiveData<String>()
    val transactionCreate = MutableLiveData<TransactionData>()
    val successMessage = MutableLiveData<String>()

    fun createTransaction() {
        viewModelScope.launch {
            try {
                Log.d("ViewModel", "CREATE TRANSACTION")
                val transactionRequest = TransactionRequest()
                val response = repository.createTransaction(transactionRequest)
                transactionCreate.value = response.data
                successMessage.value = response.message
            } catch (e: Exception) {
                errorMessage.value = e.message.toString()
                Log.d("ViewModel", errorMessage.value)
            }
        }
    }

    fun redeemReward(rewardId: Int) {
        viewModelScope.launch {
            try {
                Log.d("ViewModel", "CREATE TRANSACTION")
                val redeemRequest = RedeemRequest(rewardId)
                val response = repository.redeemReward(redeemRequest)
                transactionCreate.value = response.data
                successMessage.value = response.message
            } catch (e: Exception) {
                errorMessage.value = e.message.toString()
                Log.d("ViewModel", errorMessage.value)
            }
        }
    }

    fun getTransactionHistory() {
        viewModelScope.launch {
            try {
                transactionHistoryList.value = repository.getTransactionHistory().data
            } catch (e: Exception) {
                errorMessage.value = e.message.toString()
            }
        }
    }


}