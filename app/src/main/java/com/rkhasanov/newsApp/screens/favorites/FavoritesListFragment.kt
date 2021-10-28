package com.rkhasanov.newsApp.screens.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.rkhasanov.newsApp.R
import com.rkhasanov.newsApp.databinding.FragmentFavoritesListBinding
import com.rkhasanov.newsApp.databinding.FragmentNewsListBinding
import com.rkhasanov.newsApp.model.pojo.Article
import com.rkhasanov.newsApp.model.pojo.RequestResult
import com.rkhasanov.newsApp.screens.article.ArticleFragmentViewModel
import com.rkhasanov.newsApp.screens.newsList.NewsListAdapter
import com.rkhasanov.newsApp.screens.newsList.NewsListFragmentViewModel
import com.rkhasanov.newsApp.utils.APP_CONTEXT
import com.rkhasanov.newsApp.utils.toastPopUp

class FavoritesListFragment : Fragment() {

    private var _binding: FragmentFavoritesListBinding? = null
    private val binding get() = _binding!!
    private lateinit var favoritesListViewModel: FavoritesListFragmentViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NewsListAdapter

    private lateinit var favoritesListObserver: Observer<List<Article>>

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
        favoritesListViewModel = ViewModelProvider(this).get(FavoritesListFragmentViewModel::class.java)
        adapter = NewsListAdapter()
        recyclerView = binding.favoritesListRecyclerView
        recyclerView.adapter = adapter

        favoritesListObserver = Observer {
            adapter.setArticlesList(it.asReversed())
        }
        favoritesListViewModel.getArticles().observe(this, favoritesListObserver)

        favoritesListViewModel.fetchFavoriteArticles {
            // cannot show popup Why?
        }
    }

    companion object {
        fun onArticleClick(article: Article) {
            val bundle = Bundle()
            bundle.putSerializable("article", article)
            APP_CONTEXT.navController.navigate(R.id.action_favoritesListFragment_to_articleFragment, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        favoritesListViewModel.getArticles().removeObserver(favoritesListObserver)
        recyclerView.adapter = null
    }

}