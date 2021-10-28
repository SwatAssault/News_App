package com.rkhasanov.newsApp.screens.newsList

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.rkhasanov.newsApp.R
import com.rkhasanov.newsApp.databinding.FragmentNewsListBinding
import com.rkhasanov.newsApp.model.pojo.RequestResult
import com.rkhasanov.newsApp.utils.APP_CONTEXT

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
        adapter.onItemClick = {
            val bundle = Bundle()
            bundle.putSerializable("article", it)
            APP_CONTEXT.navController.navigate(R.id.action_newsListFragment_to_articleFragment, bundle)
        }

        loadingCircle = binding.loadingCircle

        newsListObserver = Observer {
            adapter.setArticlesList(it.articles?.asReversed()!!)
            loadingCircle.visibility = View.GONE
        }

        newsListViewModel.getRequestResult().observe(this, newsListObserver)

        binding.fetchNewsButton.setOnClickListener {
            getNews()
        }

        getNews()
    }

    private fun getNews() {
        loadingCircle.visibility = View.VISIBLE
        newsListViewModel.fetch {
            // cannot show popup Why?
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        newsListViewModel.getRequestResult().removeObserver(newsListObserver)
        recyclerView.adapter = null
    }

}