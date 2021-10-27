package com.rkhasanov.newsApp.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import com.rkhasanov.newsApp.R
import com.rkhasanov.newsApp.databinding.ActivityMainBinding
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.rkhasanov.newsApp.utils.APP_CONTEXT


class MainActivity : AppCompatActivity()  {

    lateinit var toolbar: Toolbar
    lateinit var navController: NavController
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        APP_CONTEXT = this
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        toolbar = binding.toolbar
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.navigation_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.go_to_favorites -> {
                APP_CONTEXT.navController.navigate(R.id.favoritesListFragment)
            }

            R.id.go_to_news_list -> {
                APP_CONTEXT.navController.navigate(R.id.newsListFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}