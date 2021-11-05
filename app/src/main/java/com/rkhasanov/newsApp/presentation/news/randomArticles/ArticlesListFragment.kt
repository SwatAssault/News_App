package com.rkhasanov.newsApp.presentation.news.randomArticles

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.rkhasanov.newsApp.R
import com.rkhasanov.newsApp.databinding.FragmentArticlesListBinding
import com.rkhasanov.newsApp.extentions.launchWhenStarted
import com.rkhasanov.newsApp.presentation.news.ArticleState
import com.rkhasanov.newsApp.utils.APP_CONTEXT
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class ArticlesListFragment : Fragment() {

    private var _binding: FragmentArticlesListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ArticlesListFragmentViewModel by viewModels()

    private lateinit var adapter: ArticlesListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArticlesListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        init()
    }

    private fun init() {
        adapter = ArticlesListAdapter()
        binding.newsListRecyclerView.adapter = adapter
        adapter.onItemClick = {
            val bundle = Bundle()
            bundle.putSerializable("article", it)
            APP_CONTEXT.navController.navigate(R.id.action_newsListFragment_to_articleFragment, bundle)
        }

        binding.fetchNewsButton.setOnClickListener {
            viewModel.getRandomArticles()
        }

        viewModel.articles.onEach {
            when(it) {
                is ArticleState.Loading -> {
                    binding.loadingCircle.visibility = View.VISIBLE
                }

                is ArticleState.Error -> {
                    binding.loadingCircle.visibility = View.GONE
                }

                is ArticleState.Success -> {
                    binding.loadingCircle.visibility = View.GONE
                    adapter.setArticlesList(it.articles.asReversed())
                }
            }
        }.launchWhenStarted(lifecycleScope)

        viewModel.getRandomArticles()

    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding.newsListRecyclerView.adapter = null
        _binding = null
    }

}