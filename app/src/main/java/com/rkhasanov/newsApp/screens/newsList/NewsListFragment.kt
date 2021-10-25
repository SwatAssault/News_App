package com.rkhasanov.newsApp.screens.newsList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.rkhasanov.newsApp.R
import com.rkhasanov.newsApp.databinding.ActivityMainBinding
import com.rkhasanov.newsApp.databinding.FragmentNewsListBinding
import com.rkhasanov.newsApp.model.pojo.Article

class NewsListFragment : Fragment() {

    private var _binding: FragmentNewsListBinding? = null
    private val binding get() = _binding!!
    private lateinit var newsListViewModel: NewsListFragmentViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NewsListAdapter
    private lateinit var newsListObserver: Observer<List<Article>>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        init()
    }

    private fun init() {
        newsListViewModel = ViewModelProvider(this).get(NewsListFragmentViewModel::class.java)
        adapter = NewsListAdapter()
        recyclerView = binding.newsListRecyclerView
        recyclerView.adapter = adapter

        newsListObserver = Observer {
            adapter.setArticlesList(it.asReversed())
        }

        newsListViewModel.getArticles().observe(this, newsListObserver)

        newsListViewModel.fetch()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        newsListViewModel.getArticles().removeObserver(newsListObserver)
        recyclerView.adapter = null
    }

}