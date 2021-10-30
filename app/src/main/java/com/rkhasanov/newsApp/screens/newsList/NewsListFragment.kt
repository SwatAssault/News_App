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
import com.rkhasanov.newsApp.utils.toastPopUp
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.scopes.FragmentScoped


@AndroidEntryPoint
class NewsListFragment : Fragment() {

    private var _binding: FragmentNewsListBinding? = null
    private val binding get() = _binding!!
    private val newsListViewModel: NewsListFragmentViewModel by viewModels()

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
        adapter = NewsListAdapter()
        binding.newsListRecyclerView.adapter = adapter
        adapter.onItemClick = {
            val bundle = Bundle()
            bundle.putSerializable("article", it)
            APP_CONTEXT.navController.navigate(R.id.action_newsListFragment_to_articleFragment, bundle)
        }

        newsListObserver = Observer {
            adapter.setArticlesList(it.asReversed())
            binding.loadingCircle.visibility = View.GONE
        }

        newsListViewModel.articles.observe(this, newsListObserver)

        binding.fetchNewsButton.setOnClickListener {
            getNews()
        }

        getNews()
    }

    private fun getNews() {
        binding.loadingCircle.visibility = View.VISIBLE
        newsListViewModel.fetchNews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        newsListViewModel.articles.removeObserver(newsListObserver)
        binding.newsListRecyclerView.adapter = null
        _binding = null
    }

}