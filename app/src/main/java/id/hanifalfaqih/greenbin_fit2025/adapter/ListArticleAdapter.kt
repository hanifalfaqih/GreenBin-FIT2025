package id.hanifalfaqih.greenbin_fit2025.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.hanifalfaqih.greenbin_fit2025.databinding.ArticleItemCardBinding
import id.hanifalfaqih.greenbin_fit2025.model.response.article.ArticleItem

class ListArticleAdapter(private val articleId: (Int) -> Unit): ListAdapter<ArticleItem, ListArticleAdapter.ArticleViewHolder>(ArticleComparator()) {

    class ArticleComparator : DiffUtil.ItemCallback<ArticleItem>() {
        override fun areItemsTheSame(
            oldItem: ArticleItem,
            newItem: ArticleItem
        ): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(
            oldItem: ArticleItem,
            newItem: ArticleItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArticleViewHolder {
        val binding =
            ArticleItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ArticleViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }

    inner class ArticleViewHolder(private val binding: ArticleItemCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ArticleItem) {
            binding.also {
                it.articleTitle.text = data.title

                Glide.with(this.itemView)
                    .load(data.image)
                    .into(it.articleImage)

                itemView.setOnClickListener {
                    articleId.invoke(data.id.toInt())
                }
            }
        }
    }
}