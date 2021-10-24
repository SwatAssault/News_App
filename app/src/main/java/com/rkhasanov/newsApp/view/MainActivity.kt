package com.rkhasanov.newsApp.view

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.rkhasanov.newsApp.R
import com.rkhasanov.newsApp.model.pojo.newsRequest.NewsRequester
import com.rkhasanov.newsApp.viewModel.ArticlesList


class MainActivity : AppCompatActivity() {

    private var articlesViewModel = ArticlesList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        val textView = findViewById<TextView>(R.id.textView)
        //val layout = findViewById<LinearLayout>(R.id.newsScrollViewLayout)

        articlesViewModel = ViewModelProvider(this).get(ArticlesList::class.java)

        articlesViewModel.getArticles().observe(this, Observer {

            textView.text = it.articles?.get(0)?.author

            //var textView = TextView(this)
            //textView.text = "12344"
            //layout.addView(textView)
        })

        button.setOnClickListener {
            articlesViewModel.fetch()
        }
    }



}