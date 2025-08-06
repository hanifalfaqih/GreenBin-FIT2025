package id.hanifalfaqih.greenbin_fit2025.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.hanifalfaqih.greenbin_fit2025.model.response.transaction.TransactionHistoryItem

class ListHistoryAdapter: ListAdapter<TransactionHistoryItem, ListHistoryAdapter.HistoryViewHolder>(HistoryComparator()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListHistoryAdapter.HistoryViewHolder {
        val binding = HistoryItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ListHistoryAdapter.HistoryViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }

    inner class HistoryViewHolder(private val binding: HistoryItemCardBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: TransactionHistoryItem) {
            //TODO 2
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