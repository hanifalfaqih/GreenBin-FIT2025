package id.hanifalfaqih.greenbin_fit2025.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import id.hanifalfaqih.greenbin_fit2025.databinding.FragmentHomeBinding
import id.hanifalfaqih.greenbin_fit2025.ui.calculator_point.CalculatorPointActivity
import id.hanifalfaqih.greenbin_fit2025.ui.history.HistoryActivity
import id.hanifalfaqih.greenbin_fit2025.ui.reward.RewardActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRedeemPoint.setOnClickListener {
            val intent = Intent(requireContext(), RewardActivity::class.java)
            startActivity(intent)
        }

        binding.btnCalculatorPoint.setOnClickListener {
            val intent = Intent(requireContext(), CalculatorPointActivity::class.java)
            startActivity(intent)
        }

        binding.btnHistory.setOnClickListener {
            val intent = Intent(requireContext(), HistoryActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}