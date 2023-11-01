package com.example.comics.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.comics.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels
import com.example.comics.utils.gone
import com.example.comics.utils.visible

@AndroidEntryPoint
class ComicsActivity: AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: ComicsViewModel by viewModels()

    private val comicsListAdapter: ComicsListAdapter by lazy {
        ComicsListAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupView()
        setupListeners()
        fetchComics()
    }

    private fun setupView(){
        with(binding) {
            listItem.adapter = comicsListAdapter
            swipeRefresh.setOnRefreshListener(::fetchComics)
        }
    }

    private fun setupListeners() {
        viewModel.comicsList.observe(this){
            binding.errorTV.gone()
            binding.listItem.visible()

            comicsListAdapter.submitList(it)
        }

        viewModel.loadState.observe(this){
            binding.swipeRefresh.isRefreshing = it
        }

        viewModel.errorState.observe(this) {
            binding.errorTV.visible(it)
        }
    }

    private fun fetchComics(){
        viewModel.getComics()
    }
}