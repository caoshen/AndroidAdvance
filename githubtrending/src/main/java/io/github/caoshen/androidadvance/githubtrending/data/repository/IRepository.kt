package io.github.caoshen.androidadvance.githubtrending.data.repository

import io.github.caoshen.androidadvance.githubtrending.data.entity.RepoList
import io.github.caoshen.androidadvance.githubtrending.data.entity.ResultX

interface IRepository {
    suspend fun getRepoList(): ResultX<RepoList>
}