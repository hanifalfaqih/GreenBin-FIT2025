package id.hanifalfaqih.greenbin_fit2025.ui.login

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
import id.hanifalfaqih.greenbin_fit2025.databinding.ActivityLoginBinding
import id.hanifalfaqih.greenbin_fit2025.ui.register.RegisterActivity
import id.hanifalfaqih.greenbin_fit2025.util.TokenManager
import id.hanifalfaqih.greenbin_fit2025.viewmodel.AuthViewModel
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tokenManager = TokenManager(applicationContext)
        authViewModel = AuthViewModel(tokenManager)

        binding.loginButton.setOnClickListener {
            validateLogin()
        }

        authViewModel.successLogin.observe(this) { isSuccess ->
            if (isSuccess) {
                authViewModel.successMessage.observe(this@LoginActivity) { successMessage ->
                    Toast.makeText(this@LoginActivity, successMessage, Toast.LENGTH_SHORT).show()
                }

                val intent = Intent(this@LoginActivity, MainMenuActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

        binding.registerLinkText.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun validateLogin() {
        val email = binding.emailEt.text.toString()
        val password = binding.passwordEt.text.toString()

        if (email.isEmpty() && password.isEmpty()) {
            Toast.makeText(this, "Email dan password harus diisi", Toast.LENGTH_SHORT).show()
        } else {
            authViewModel.login(email, password)
        }
    }
}