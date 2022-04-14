package io.github.caoshen.androidadvance.githubtrending.domain

import io.github.caoshen.androidadvance.githubtrending.data.entity.RepoList
import io.github.caoshen.androidadvance.githubtrending.data.entity.ResultX
import io.github.caoshen.androidadvance.githubtrending.data.repository.IRepository
import io.github.caoshen.androidadvance.githubtrending.data.repository.MainRepository

class GetRepoListUseCase(
        private val repository: IRepository = MainRepository()
) {
    suspend operator fun invoke(): ResultX<RepoList> {
        return repository.getRepoList()
    }
}