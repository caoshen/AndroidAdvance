package io.github.caoshen.androidadvance.githubtrending.data.source

import io.github.caoshen.androidadvance.githubtrending.data.entity.RepoList
import io.github.caoshen.androidadvance.githubtrending.data.entity.ResultX

interface RepoDataSource {
    suspend fun getRepos(): ResultX<RepoList>
}