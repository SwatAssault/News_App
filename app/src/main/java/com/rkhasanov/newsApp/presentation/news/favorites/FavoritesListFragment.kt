package com.rkhasanov.newsApp.presentation.news.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.rkhasanov.newsApp.R
import com.rkhasanov.newsApp.databinding.FragmentFavoritesListBinding
import com.rkhasanov.newsApp.extentions.launchWhenStarted
import com.rkhasanov.newsApp.presentation.news.ArticleState
import com.rkhasanov.newsApp.presentation.news.randomArticles.ArticlesListAdapter
import com.rkhasanov.newsApp.utils.APP_CONTEXT
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class FavoritesListFragment : Fragment() {

    private var _binding: FragmentFavoritesListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FavoritesListFragmentViewModel by viewModels()

    private lateinit var adapter: ArticlesListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        init()
    }

    private fun init() {
        adapter = ArticlesListAdapter()
        binding.favoritesListRecyclerView.adapter = adapter
        adapter.onItemClick = {
            val bundle = Bundle()
            bundle.putSerializable("article", it)
            APP_CONTEXT.navController.navigate(R.id.action_favoritesListFragment_to_articleFragment, bundle)
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
                    adapter.setArticlesList(it.articles.asReversed())
                    binding.loadingCircle.visibility = View.GONE
                }
            }
        }.launchWhenStarted(lifecycleScope)

        viewModel.loadArticles()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        binding.favoritesListRecyclerView.adapter = null
    }

}