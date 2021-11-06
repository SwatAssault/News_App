package com.rkhasanov.newsApp.presentation.news.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.rkhasanov.newsApp.R
import com.rkhasanov.newsApp.databinding.FragmentArticleBinding
import com.rkhasanov.newsApp.domain.model.Article
import com.rkhasanov.newsApp.extentions.launchWhenStarted
import com.rkhasanov.newsApp.presentation.BasicRequestState
import com.rkhasanov.newsApp.presentation.news.ArticleState
import com.rkhasanov.newsApp.utils.toastPopUp
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class ArticleFragment : Fragment() {

    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ArticleFragmentViewModel by viewModels()
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
        //val date: LocalDate = currentArticle.publishedAt?.toInstant()?.atZone(ZoneId.systemDefault())?.toLocalDate()!!
        //binding.articleCardPublishDate.text = getString(R.string.article_published_on, date.format(formatter))
        val requestOptions = RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .format(DecodeFormat.PREFER_RGB_565)
        Glide.with(this)
            .load(currentArticle.urlToImage)
            .apply(requestOptions)
            .into(binding.articleCardImage)
        binding.articleCardContent.text = currentArticle.content

        binding.addToFavorites.setOnClickListener {
            viewModel.addToFavorites(currentArticle)
        }

        viewModel.article.onEach {
            when(it) {
                is BasicRequestState.Success -> {
                    toastPopUp(getString(R.string.add_to_favorites))
                }
                is BasicRequestState.Error -> {}
                BasicRequestState.Loading -> {}
            }
        }.launchWhenStarted(lifecycleScope)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}