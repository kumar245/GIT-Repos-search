package dev.chaitan.gitusersearch.api.service

import dev.chaitan.gitusersearch.api.END_POINT_REPO_DETAIL
import dev.chaitan.gitusersearch.api.END_POINT_SEARCH_USER
import dev.chaitan.gitusersearch.api.END_POINT_USER_DETAIL
import dev.chaitan.gitusersearch.model.Repo
import dev.chaitan.gitusersearch.model.response.UserDetailResponse
import dev.chaitan.gitusersearch.model.response.UserRepoResponse
import dev.chaitan.gitusersearch.model.response.UserSearchResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitService {
    @GET(END_POINT_SEARCH_USER)
    fun searchUser(
        @Query("q") searchKeyword: String,
        @Query("sort") shortBy: String,
        @Query("order") orderBy: String
    ): Observable<UserSearchResponse>

    @GET(END_POINT_USER_DETAIL)
    fun getUserDetail(
        @Path("login") login: String
    ): Observable<UserDetailResponse>

    @GET(END_POINT_REPO_DETAIL)
    fun getUserRepo(
        @Path("login") login: String
    ): Observable<List<Repo>>
}