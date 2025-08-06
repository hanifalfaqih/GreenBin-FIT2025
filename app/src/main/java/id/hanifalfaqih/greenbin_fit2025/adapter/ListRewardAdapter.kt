package id.hanifalfaqih.greenbin_fit2025.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.hanifalfaqih.greenbin_fit2025.databinding.RewardItemCardBinding
import id.hanifalfaqih.greenbin_fit2025.model.response.reward.RewardItem

class ListRewardAdapter(private val rewardId: (Int) -> Unit): ListAdapter<RewardItem, ListRewardAdapter.RewardViewHolder>(RewardComparator()) {

    inner class RewardViewHolder(private val binding: RewardItemCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: RewardItem) {
            binding.also {
                Glide.with(this.itemView)
                    .load(data.image)
                    .into(it.articleImage)

                it.rewardName.text = data.title
                it.rewardPoint.text = data.point.toString()

                itemView.setOnClickListener {
                    rewardId.invoke(data.id.toInt())
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListRewardAdapter.RewardViewHolder {
        val binding = RewardItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RewardViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ListRewardAdapter.RewardViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }

    class RewardComparator: DiffUtil.ItemCallback<RewardItem>() {
        override fun areItemsTheSame(
            oldItem: RewardItem,
            newItem: RewardItem
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: RewardItem,
            newItem: RewardItem
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }


}