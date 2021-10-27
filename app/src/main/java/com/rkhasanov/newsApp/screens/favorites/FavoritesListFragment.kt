package com.rkhasanov.newsApp.screens.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rkhasanov.newsApp.R
import com.rkhasanov.newsApp.databinding.FragmentFavoritesListBinding
import com.rkhasanov.newsApp.databinding.FragmentNewsListBinding

class FavoritesListFragment : Fragment() {

    private var _binding: FragmentFavoritesListBinding? = null
    private val binding get() = _binding!!

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

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}