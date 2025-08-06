package id.hanifalfaqih.greenbin_fit2025.ui.history

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
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
        enableEdgeToEdge()

        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tokenManager = TokenManager(applicationContext)
        transactionViewModel = TransactionViewModel(tokenManager)

        binding.backButton.setOnClickListener {
            val intent = Intent(this, MainMenuActivity::class.java)
            startActivity(intent)
            finish()
        }

        transactionViewModel.getTransactionHistory()

        transactionViewModel.transactionHistoryList.observe(this) { historyList ->
            historyAdapter.submitList(historyList)
        }

        historyAdapter = ListHistoryAdapter()
        binding.historyRv.layoutManager = GridLayoutManager(this, 2)

    }
}