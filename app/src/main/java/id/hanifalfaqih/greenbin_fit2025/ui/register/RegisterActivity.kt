package id.hanifalfaqih.greenbin_fit2025.ui.register

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import id.hanifalfaqih.greenbin_fit2025.MainMenuActivity
import id.hanifalfaqih.greenbin_fit2025.R
import id.hanifalfaqih.greenbin_fit2025.databinding.ActivityRegisterBinding
import id.hanifalfaqih.greenbin_fit2025.util.TokenManager
import id.hanifalfaqih.greenbin_fit2025.viewmodel.AuthViewModel
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tokenManager = TokenManager(applicationContext)
        authViewModel = AuthViewModel(tokenManager)

        binding.registerButton.setOnClickListener {
            validateRegister()
        }

        authViewModel.successRegister.observe(this) { isSuccess ->
            if (isSuccess) {
                authViewModel.successMessage.observe(this) { successMessage ->
                    Toast.makeText(this, successMessage, Toast.LENGTH_SHORT).show()
                }

                startActivity(Intent(this, MainMenuActivity::class.java))
                finish()
            } else {
                Toast.makeText(this, "Registrasi gagal", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateRegister() {
        val username = binding.usernameEt.text.toString()
        val password = binding.passwordEt.text.toString()
        val confirmPassword = binding.confirmPasswordEt.text.toString()
        val email = binding.emailEt.text.toString()

        when {
            username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() -> {
                Toast.makeText(this, "Semua field harus diisi",Toast.LENGTH_SHORT).show()
            }
            password.length < 8 -> {
                Toast.makeText(this, "Password minimal 8 karakter",Toast.LENGTH_SHORT).show()
            }
            password != confirmPassword -> {
                Toast.makeText(this, "Password dan konfirmasi harus sama", Toast.LENGTH_SHORT).show()
            }
            else -> {
                authViewModel.register(username, email, password)
            }
        }
    }
}