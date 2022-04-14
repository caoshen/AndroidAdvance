package io.github.caoshen.androidadvance.githubtrending.data.source.remote

import android.util.Log
import io.github.caoshen.androidadvance.githubtrending.data.entity.RepoList
import io.github.caoshen.androidadvance.githubtrending.data.entity.ResultX
import io.github.caoshen.androidadvance.githubtrending.data.source.RepoDataSource
import io.github.caoshen.androidadvance.githubtrending.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object RemoteRepoDataSource : RepoDataSource {
    const val TAG = "RemoteRepoDataSource"

    override suspend fun getRepos(): ResultX<RepoList> =
            withContext(Dispatchers.IO) {
                try {
                    ResultX.Success(RetrofitClient.service.repos())
                } catch (e: Exception) {
                    Log.e(TAG, e.message, e)
                    ResultX.Error(e)
                }
            }
}