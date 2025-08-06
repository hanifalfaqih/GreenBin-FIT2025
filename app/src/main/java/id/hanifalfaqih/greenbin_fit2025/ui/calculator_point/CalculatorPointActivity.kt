package id.hanifalfaqih.greenbin_fit2025.ui.calculator_point

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import id.hanifalfaqih.greenbin_fit2025.MainMenuActivity
import id.hanifalfaqih.greenbin_fit2025.R
import id.hanifalfaqih.greenbin_fit2025.databinding.ActivityCalculatorPointBinding

class CalculatorPointActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCalculatorPointBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityCalculatorPointBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.calculateButton.setOnClickListener {
            val weightInput = binding.weightEt.text.toString()
            val weight = weightInput.toIntOrNull()

            if (weight != null) {
                calculatePoint(weight)
            } else {
                Toast.makeText(this, "Masukkan berat yang valid", Toast.LENGTH_SHORT).show()
            }
        }

        binding.backButton.setOnClickListener {
            val intent = Intent(this, MainMenuActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun calculatePoint(weight: Int) {
        val point = (weight * 0.01).toInt()
        binding.resultText.text = point.toString()
    }
}