package id.hanifalfaqih.greenbin_fit2025.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.hanifalfaqih.greenbin_fit2025.R
import id.hanifalfaqih.greenbin_fit2025.databinding.HistoryItemCardBinding
import id.hanifalfaqih.greenbin_fit2025.model.response.transaction.TransactionHistoryItem
import id.hanifalfaqih.greenbin_fit2025.util.setFormattedDateTime

class ListHistoryAdapter: ListAdapter<TransactionHistoryItem, ListHistoryAdapter.HistoryViewHolder>(HistoryComparator()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListHistoryAdapter.HistoryViewHolder {
        val binding = HistoryItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.BAKLAVA)
    override fun onBindViewHolder(
        holder: ListHistoryAdapter.HistoryViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }

    inner class HistoryViewHolder(private val binding: HistoryItemCardBinding): RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.BAKLAVA)
        fun bind(data: TransactionHistoryItem) {
            binding.also {
                if (data.status_point == 1) {
                    it.historyTitle.text = "Tambah Poin"
                    it.historyPoint.text = "+${data.point}"
                    it.historyPoint.resources.getColor(R.color.primary_second, null)
                    setFormattedDateTime(it.historyDate, data.created_at)
                } else if (data.status_point == 0) {
                    it.historyTitle.text = "Tukar Hadiah"
                    it.historyPoint.text = "-${data.point}"
                    it.historyPoint.resources.getColor(R.color.red, null)
                    setFormattedDateTime(it.historyDate, data.created_at)
                }
            }
        }
    }

    class HistoryComparator: DiffUtil.ItemCallback<TransactionHistoryItem>() {
        override fun areItemsTheSame(
            oldItem: TransactionHistoryItem,
            newItem: TransactionHistoryItem
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: TransactionHistoryItem,
            newItem: TransactionHistoryItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

    }

}