package com.rkhasanov.newsApp.screens.article

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.rkhasanov.newsApp.R
import com.rkhasanov.newsApp.databinding.FragmentArticleBinding
import com.rkhasanov.newsApp.databinding.FragmentNewsListBinding
import com.rkhasanov.newsApp.model.pojo.Article
import com.rkhasanov.newsApp.model.pojo.RequestResult
import com.rkhasanov.newsApp.screens.newsList.NewsListAdapter
import com.rkhasanov.newsApp.screens.newsList.NewsListFragmentViewModel
import com.rkhasanov.newsApp.utils.toastPopUp

class ArticleFragment : Fragment() {

    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding!!
    private lateinit var articleFragmentViewModel: ArticleFragmentViewModel
    private lateinit var currentArticle: Article

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArticleBinding.inflate(layoutInflater, container, false)
        currentArticle = arguments?.getSerializable("article") as Article
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        init()
    }

    private fun init() {
        binding.articleTitle.text = currentArticle.title
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}