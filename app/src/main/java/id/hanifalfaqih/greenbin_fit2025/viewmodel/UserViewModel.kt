package id.hanifalfaqih.greenbin_fit2025.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.hanifalfaqih.greenbin_fit2025.model.response.user.ProfileData
import id.hanifalfaqih.greenbin_fit2025.network.client.RetrofitInstance
import id.hanifalfaqih.greenbin_fit2025.network.service.UserService
import id.hanifalfaqih.greenbin_fit2025.repository.UserRepository
import id.hanifalfaqih.greenbin_fit2025.util.TokenManager
import kotlinx.coroutines.launch

class UserViewModel(private val tokenManager: TokenManager) : ViewModel() {

    private val userService = RetrofitInstance.getInstance().create(UserService::class.java)

    private val repository by lazy {
        UserRepository(
            userService, tokenManager
        )
    }

    val profileData = MutableLiveData<ProfileData>()
    val point = MutableLiveData<Int>()
    val errorMessage = MutableLiveData<String>()

    fun getProfile() {
        viewModelScope.launch {
            try {
                profileData.value = repository.getProfile().data
            } catch (e: Exception) {
                errorMessage.value = e.message.toString()
            }
        }
    }

    fun getPoint() {
        viewModelScope.launch {
            try {
                point.value = repository.getPoint().data.point
            } catch (e: Exception) {
                errorMessage.value = e.message.toString()
            }
        }
    }

}