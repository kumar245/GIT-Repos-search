package dev.chaitan.gitusersearch.api.repository

import dev.chaitan.gitusersearch.api.service.GitService
import dev.chaitan.gitusersearch.model.Repo
import dev.chaitan.gitusersearch.model.response.UserDetailResponse
import dev.chaitan.gitusersearch.model.response.UserRepoResponse
import dev.chaitan.gitusersearch.model.response.UserSearchResponse
import io.reactivex.Observable

class GitRepository(gitService: GitService) {
    private val gitService = gitService

    fun searchUser(
        searchKeyword: String,
        shortBy: String,
        orderBy: String
    ): Observable<UserSearchResponse> {
        return gitService.searchUser(searchKeyword, shortBy, orderBy)
    }

    fun fetchUserDetails(
        login: String
    ): Observable<UserDetailResponse> {
        return gitService.getUserDetail(login)
    }

    fun fetchUserRepo(
        login: String
    ): Observable<List<Repo>> {
        return gitService.getUserRepo(login)
    }
}