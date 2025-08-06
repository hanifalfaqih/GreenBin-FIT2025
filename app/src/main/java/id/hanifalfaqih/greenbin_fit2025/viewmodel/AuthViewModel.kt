package id.hanifalfaqih.greenbin_fit2025.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.hanifalfaqih.greenbin_fit2025.network.client.RetrofitInstance
import id.hanifalfaqih.greenbin_fit2025.network.service.AuthService
import id.hanifalfaqih.greenbin_fit2025.repository.AuthRepository
import id.hanifalfaqih.greenbin_fit2025.util.TokenManager
import kotlinx.coroutines.launch

class AuthViewModel(private val tokenManager: TokenManager) : ViewModel() {

    private val authService = RetrofitInstance.getInstance().create(AuthService::class.java)
    private val repository by lazy {
        AuthRepository(authService, tokenManager)
    }

    val token = MutableLiveData<String>()
    val successMessage = MutableLiveData<String>()
    val successRegister = MutableLiveData<Boolean>()
    val successLogin = MutableLiveData<Boolean>()
    val successLogout = MutableLiveData<Boolean>()

    fun register(name: String, email: String, password: String) {
        viewModelScope.launch {
            try {
                val response = repository.register(name, email, password)
                successMessage.value = response.message
                successRegister.value = true
            } catch (e: Exception) {
                val response = repository.register(name, email, password)
                successMessage.value = response.message
                successRegister.value = false
            }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val response = repository.login(email, password)
                tokenManager.saveToken(response.data.access_token)
                successMessage.value = response.message
                successLogin.value = true
            } catch (e: Exception) {
                val response = repository.login(email, password)
                successMessage.value = response.message
                successLogin.value = false
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            try {
                val response = repository.logout()
                successMessage.value = response.code
                tokenManager.clearToken()
            } catch (e: Exception) {
                val response = repository.logout()
                successMessage.value = response.code
                Log.d("AuthViewModel", e.message.toString())
            }
        }
    }
}