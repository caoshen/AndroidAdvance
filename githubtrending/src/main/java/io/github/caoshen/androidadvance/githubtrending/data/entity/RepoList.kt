package io.github.caoshen.androidadvance.githubtrending.data.entity

data class RepoList(
        val count: Int = 0,
        val items: List<Repo> = listOf(),
        val msg: String = "数据为空"
)

data class Repo(
    val added_stars: String = "",
    val avatars: List<String> = listOf(),
    val desc: String = "",
    val forks: String = "",
    val lang: String = "",
    val repo: String = "",
    val repo_link: String = "",
    val stars: String = ""
)