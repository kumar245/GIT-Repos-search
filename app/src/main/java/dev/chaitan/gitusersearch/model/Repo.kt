package dev.chaitan.gitusersearch.model

data class Repo(
    val id: Long,
    val name: String,
    val full_name: String,
    val private: Boolean,
    val description: String,
    val size: Int,
    val watchers_count: Int,
    val forks_count: Int,
    val stargazers_count: Int

)