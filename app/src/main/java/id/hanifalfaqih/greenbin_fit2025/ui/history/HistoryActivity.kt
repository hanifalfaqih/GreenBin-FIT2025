package id.hanifalfaqih.greenbin_fit2025.ui.history

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import id.hanifalfaqih.greenbin_fit2025.MainMenuActivity
import id.hanifalfaqih.greenbin_fit2025.R
import id.hanifalfaqih.greenbin_fit2025.adapter.ListHistoryAdapter
import id.hanifalfaqih.greenbin_fit2025.databinding.ActivityHistoryBinding
import id.hanifalfaqih.greenbin_fit2025.util.TokenManager
import id.hanifalfaqih.greenbin_fit2025.viewmodel.TransactionViewModel

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding
    private lateinit var transactionViewModel: TransactionViewModel
    private lateinit var historyAdapter: ListHistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tokenManager = TokenManager(applicationContext)
        transactionViewModel = TransactionViewModel(tokenManager)

        transactionViewModel.getTransactionHistory()

        transactionViewModel.transactionHistoryList.observe(this) { historyList ->
            historyAdapter.submitList(historyList)
        }

        historyAdapter = ListHistoryAdapter()
        binding.historyRv.adapter = historyAdapter
        binding.historyRv.layoutManager = LinearLayoutManager(this)

        binding.backButton.setOnClickListener {
            val intent = Intent(this, MainMenuActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}