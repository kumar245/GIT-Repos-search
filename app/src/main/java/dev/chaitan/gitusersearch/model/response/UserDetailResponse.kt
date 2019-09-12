package dev.chaitan.gitusersearch.model.response

data class UserDetailResponse(
    val login: String,
    val id: Long,
    val avatar_url: String,
    val url: String,
    val name: String,
    val location: String,
    val email: String,
    val bio: String,
    val public_repos: String,
    val public_gists: String,
    val followers: Int,
    val following: Int,
    val created_at: String
)