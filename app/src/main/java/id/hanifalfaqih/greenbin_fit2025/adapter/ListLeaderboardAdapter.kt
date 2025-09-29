package id.hanifalfaqih.greenbin_fit2025.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.hanifalfaqih.greenbin_fit2025.databinding.LeaderboardItemCardBinding
import id.hanifalfaqih.greenbin_fit2025.model.response.leaderboard.LeaderboardItem

class ListLeaderboardAdapter :
    ListAdapter<LeaderboardItem, ListLeaderboardAdapter.LeaderboardViewHolder>(LeaderboardComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeaderboardViewHolder {
        val binding = LeaderboardItemCardBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return LeaderboardViewHolder(binding)
    }

    inner class LeaderboardViewHolder(private val binding: LeaderboardItemCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: LeaderboardItem, position: Int) {
            binding.tvName.text = data.name
            binding.tvPoints.text = "${data.total_points} pts"
            binding.tvRank.text = (position + 4).toString() // Rank 1–3 tidak dimasukin ke rv
        }
    }

    override fun onBindViewHolder(holder: LeaderboardViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    class LeaderboardComparator : DiffUtil.ItemCallback<LeaderboardItem>() {
        override fun areItemsTheSame(oldItem: LeaderboardItem, newItem: LeaderboardItem): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: LeaderboardItem, newItem: LeaderboardItem): Boolean {
            return oldItem == newItem
        }
    }
}
