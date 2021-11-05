package com.rkhasanov.newsApp.presentation.news.randomArticles

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.rkhasanov.newsApp.R
import com.rkhasanov.newsApp.domain.model.Article
import kotlinx.android.synthetic.main.article_item.view.*


class ArticlesListAdapter : RecyclerView.Adapter<ArticlesListAdapter.NewsHolder>() {

    var onItemClick: ((Article) -> Unit)? = null
    private var articlesList = emptyList<Article>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.article_item, parent, false)
        return NewsHolder(view)
    }

    override fun onViewAttachedToWindow(holder: NewsHolder) {
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(articlesList[holder.adapterPosition])
        }
    }

    override fun onViewDetachedFromWindow(holder: NewsHolder) {
        holder.itemView.setOnClickListener(null)
        super.onViewDetachedFromWindow(holder)
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        holder.articleTitle.text = articlesList[position].title
        holder.articleDescription.text = articlesList[position].description

        val requestOptions = RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .format(DecodeFormat.PREFER_RGB_565)

        Glide.with(holder.itemView)
            .load(articlesList[position].urlToImage)
            .apply(requestOptions)
            .into(holder.articleImageUrl)
    }

    override fun getItemCount(): Int = articlesList.size

    fun setArticlesList(list: List<Article>) {
        articlesList = list
        notifyDataSetChanged()
    }

    class NewsHolder(view: View) : RecyclerView.ViewHolder(view) {
        val articleTitle: TextView = view.article_item_title
        val articleDescription: TextView = view.article_item_desc
        val articleImageUrl: ImageView = view.article_item_image
    }

}