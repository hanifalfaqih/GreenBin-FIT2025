package id.hanifalfaqih.greenbin_fit2025

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import id.hanifalfaqih.greenbin_fit2025.ui.onboarding.LandingActivity
import id.hanifalfaqih.greenbin_fit2025.util.TokenManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class SplashActivity : AppCompatActivity() {

    private lateinit var tokenManager: TokenManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        tokenManager = TokenManager(applicationContext)

        CoroutineScope(Dispatchers.Main).launch {
            delay(2000)
            tokenManager.tokenFlow.collect { token ->
                Toast.makeText(this@SplashActivity, token.toString(), Toast.LENGTH_SHORT).show()
                Log.d("SplashActivity", token.toString())
                if (token.isNullOrEmpty()) {
                    startActivity(Intent(this@SplashActivity, LandingActivity::class.java))
                    finish()
                } else {
                    startActivity(Intent(this@SplashActivity, MainMenuActivity::class.java))
                    finish()
                }
            }
        }

//        runBlocking {
//
//        }
//
//        Handler().postDelayed({
//
//        }, 2000)
    }
}