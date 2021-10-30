package com.rkhasanov.newsApp.screens.newsList

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ProgressBar
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.rkhasanov.newsApp.R
import com.rkhasanov.newsApp.databinding.FragmentNewsListBinding
import com.rkhasanov.newsApp.model.pojo.Article
import com.rkhasanov.newsApp.utils.APP_CONTEXT


class NewsListFragment : Fragment() {

    private var _binding: FragmentNewsListBinding? = null
    private val binding get() = _binding!!
    private val newsListViewModel: NewsListFragmentViewModel by viewModels()

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NewsListAdapter
    private lateinit var newsListObserver: Observer<List<Article>>

    private lateinit var loadingCircle: ProgressBar

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
        adapter = NewsListAdapter()
        recyclerView = binding.newsListRecyclerView
        recyclerView.adapter = adapter
        adapter.onItemClick = {
            val bundle = Bundle()
            bundle.putSerializable("article", it)
            APP_CONTEXT.navController.navigate(R.id.action_newsListFragment_to_articleFragment, bundle)
        }

        loadingCircle = binding.loadingCircle

        newsListObserver = Observer {
            adapter.setArticlesList(it.asReversed())
            loadingCircle.visibility = View.GONE
        }

        newsListViewModel.articles.observe(this, newsListObserver)

        binding.fetchNewsButton.setOnClickListener {
            getNews()
        }

        getNews()
    }

    private fun getNews() {
        loadingCircle.visibility = View.VISIBLE
        newsListViewModel.fetchNews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        newsListViewModel.articles.removeObserver(newsListObserver)
        recyclerView.adapter = null
    }

}