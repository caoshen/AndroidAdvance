package io.github.caoshen.androidadvance.githubtrending.data.repository

import io.github.caoshen.androidadvance.githubtrending.data.entity.RepoList
import io.github.caoshen.androidadvance.githubtrending.data.entity.ResultX
import io.github.caoshen.androidadvance.githubtrending.data.source.RepoDataSource
import io.github.caoshen.androidadvance.githubtrending.data.source.remote.RemoteRepoDataSource

class MainRepository(
        private val remoteRepo: RepoDataSource = RemoteRepoDataSource,
        private val localRepo: RepoDataSource? = null
) : IRepository {
    override suspend fun getRepoList(): ResultX<RepoList> = remoteRepo.getRepos()
}