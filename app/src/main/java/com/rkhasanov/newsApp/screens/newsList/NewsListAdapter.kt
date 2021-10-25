package com.rkhasanov.newsApp.screens.newsList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rkhasanov.newsApp.R
import com.rkhasanov.newsApp.model.pojo.Article
import kotlinx.android.synthetic.main.article_item.view.*


class NewsListAdapter : RecyclerView.Adapter<NewsListAdapter.NewsHolder>() {

    private var articlesList = emptyList<Article>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.article_item, parent, false)
        return NewsHolder(view)
    }

    override fun onViewAttachedToWindow(holder: NewsHolder) {
        holder.itemView.setOnClickListener {
            NewsListFragment.onArticleClick(articlesList[holder.adapterPosition])
        }
    }

    override fun onViewDetachedFromWindow(holder: NewsHolder) {
        holder.itemView.setOnClickListener(null)
        super.onViewDetachedFromWindow(holder)
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        holder.articleTitle.text = articlesList[position].title
        holder.articleDescription.text = articlesList[position].description
    }

    override fun getItemCount(): Int = articlesList.size

    fun setArticlesList(list: List<Article>) {
        articlesList = list
        notifyDataSetChanged()
    }

    class NewsHolder(view: View) : RecyclerView.ViewHolder(view) {
        val articleTitle: TextView = view.article_item_title
        val articleDescription: TextView = view.article_item_desc
    }

}