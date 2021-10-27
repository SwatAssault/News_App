package com.rkhasanov.newsApp.screens.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.rkhasanov.newsApp.R
import com.rkhasanov.newsApp.databinding.FragmentArticleBinding
import com.rkhasanov.newsApp.model.pojo.Article
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter


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
        binding.articleCardTitle.text = currentArticle.title
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        binding.articleCardAuthor.text = getString(R.string.article_author, currentArticle.author)
        val date: LocalDate = currentArticle.publishedAt?.toInstant()?.atZone(ZoneId.systemDefault())?.toLocalDate()!!
        binding.articleCardPublishDate.text = getString(R.string.article_published_on, date.format(formatter))
        val requestOptions = RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .format(DecodeFormat.PREFER_RGB_565)
        Glide.with(this)
            .load(currentArticle.urlToImage)
            .apply(requestOptions)
            .into(binding.articleCardImage)
        binding.articleCardContent.text = currentArticle.content
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}