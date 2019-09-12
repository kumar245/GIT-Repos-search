package dev.chaitan.gitusersearch.model.response

import dev.chaitan.gitusersearch.model.Repo

data class UserRepoResponse(
    val repoList: MutableList<Repo>
)