package io.github.caoshen.androidadvance.githubtrending.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import io.github.caoshen.androidadvance.githubtrending.R
import io.github.caoshen.androidadvance.githubtrending.data.entity.RepoList
import io.github.caoshen.androidadvance.githubtrending.databinding.ActivityMainBinding
import io.github.caoshen.androidadvance.githubtrending.ui.adapter.RepoAdapter

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var adapter: RepoAdapter
    private lateinit var binding: ActivityMainBinding

    private val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.loadRepos()
        observeData()
    }

    private fun observeData() {
        viewModel.repos.observe(this) {
            display(it)
        }
    }

    private fun display(it: RepoList) {
        adapter = RepoAdapter(it)
        binding.recycler.layoutManager = layoutManager
        binding.recycler.adapter = adapter
    }
}