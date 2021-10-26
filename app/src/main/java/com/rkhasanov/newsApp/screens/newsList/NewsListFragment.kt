package com.rkhasanov.newsApp.screens.newsList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.activity.addCallback
import androidx.core.widget.ContentLoadingProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.rkhasanov.newsApp.R
import com.rkhasanov.newsApp.databinding.FragmentNewsListBinding
import com.rkhasanov.newsApp.model.pojo.Article
import com.rkhasanov.newsApp.model.pojo.RequestResult
import com.rkhasanov.newsApp.utils.APP_CONTEXT
import com.rkhasanov.newsApp.utils.toastPopUp

class NewsListFragment : Fragment() {

    private var _binding: FragmentNewsListBinding? = null
    private val binding get() = _binding!!
    private lateinit var newsListViewModel: NewsListFragmentViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NewsListAdapter
    private lateinit var newsListObserver: Observer<RequestResult>

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
        newsListViewModel = ViewModelProvider(this).get(NewsListFragmentViewModel::class.java)
        adapter = NewsListAdapter()
        recyclerView = binding.newsListRecyclerView
        recyclerView.adapter = adapter
        loadingCircle = binding.loadingCircle

        newsListObserver = Observer {
            adapter.setArticlesList(it.articles?.asReversed()!!)
            loadingCircle.visibility = View.GONE
        }

        newsListViewModel.getRequestResult().observe(this, newsListObserver)

        binding.fetchNewsButton.setOnClickListener {
            loadingCircle.visibility = View.VISIBLE
            newsListViewModel.fetch {

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        newsListViewModel.getRequestResult().removeObserver(newsListObserver)
        recyclerView.adapter = null
    }

    companion object {
        fun onArticleClick(article: Article) {
            val bundle = Bundle()
            bundle.putSerializable("article", article)
            APP_CONTEXT.navController.navigate(R.id.action_newsListFragment_to_articleFragment, bundle)
        }
    }

}