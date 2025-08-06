package id.hanifalfaqih.greenbin_fit2025

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import id.hanifalfaqih.greenbin_fit2025.ui.onboarding.LandingActivity
import id.hanifalfaqih.greenbin_fit2025.util.TokenManager

class SplashActivity : AppCompatActivity() {

    private lateinit var tokenManager: TokenManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        tokenManager = TokenManager(applicationContext)

        Handler().postDelayed({
            if (tokenManager.tokenFlow.toString().isNotEmpty()) {
                startActivity(Intent(this, MainMenuActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this, LandingActivity::class.java))
                finish()
            }
        }, 2500)
    }
}