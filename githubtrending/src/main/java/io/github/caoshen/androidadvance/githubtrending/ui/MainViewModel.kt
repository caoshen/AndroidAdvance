package io.github.caoshen.androidadvance.githubtrending.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.caoshen.androidadvance.githubtrending.data.entity.RepoList
import io.github.caoshen.androidadvance.githubtrending.data.entity.ResultX
import io.github.caoshen.androidadvance.githubtrending.domain.GetRepoListUseCase
import kotlinx.coroutines.launch

class MainViewModel(private val getRepoListUseCase: GetRepoListUseCase = GetRepoListUseCase()
) : ViewModel() {

    val repos: LiveData<RepoList>
        get() = _repos

    private var _repos = MutableLiveData<RepoList>()

    fun loadRepos() {
        viewModelScope.launch {
            val repos = getRepoListUseCase()
            when (repos) {
                is ResultX.Success -> {
                    _repos.value = repos.data
                }
                is ResultX.Error -> {
                    // 空列表
                    _repos.value = RepoList()
                }
                ResultX.Loading -> {
                    // 展示 Loading
                }
            }
        }
    }
}