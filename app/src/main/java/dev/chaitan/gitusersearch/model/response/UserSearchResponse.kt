package dev.chaitan.gitusersearch.model.response

import dev.chaitan.gitusersearch.model.GitUser

data class UserSearchResponse(val total_count: Long,
                              val incomplete_results: Boolean,
                              val items: List<GitUser>)