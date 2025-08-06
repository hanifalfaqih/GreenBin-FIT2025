package id.hanifalfaqih.greenbin_fit2025.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import id.hanifalfaqih.greenbin_fit2025.R
import id.hanifalfaqih.greenbin_fit2025.databinding.FragmentProfileBinding
import id.hanifalfaqih.greenbin_fit2025.ui.login.LoginActivity
import id.hanifalfaqih.greenbin_fit2025.ui.onboarding.LandingActivity
import id.hanifalfaqih.greenbin_fit2025.util.TokenManager
import id.hanifalfaqih.greenbin_fit2025.viewmodel.AuthViewModel
import id.hanifalfaqih.greenbin_fit2025.viewmodel.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var authViewModel: AuthViewModel
    private lateinit var tokenManager: TokenManager
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tokenManager = TokenManager(requireContext())
        authViewModel = AuthViewModel(tokenManager)
        userViewModel = UserViewModel(tokenManager)

        /**
         * Get data from view model
         */
        userViewModel.getProfile()

        userViewModel.profileData.observe(viewLifecycleOwner) { profileData ->
            binding.usernameText.text = profileData.name
            binding.emailText.text = profileData.email
        }

        binding.logoutButton.setOnClickListener {
            startActivity(Intent(requireContext(), LandingActivity::class.java))
            requireActivity().finish()
        }

    }
}