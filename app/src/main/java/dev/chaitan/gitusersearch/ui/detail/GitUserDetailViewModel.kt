package dev.chaitan.gitusersearch.ui.detail

import android.os.Build
import android.text.Html
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import dev.chaitan.gitusersearch.api.repository.GitRepository
import dev.chaitan.gitusersearch.model.Repo
import dev.chaitan.gitusersearch.model.response.UserDetailResponse
import dev.chaitan.gitusersearch.model.response.UserRepoResponse
import dev.chaitan.gitusersearch.model.response.UserSearchResponse
import dev.chaitan.gitusersearch.rx.EventBus
import dev.chaitan.gitusersearch.rx.event.RxEvent
import dev.chaitan.gitusersearch.ui.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GitUserDetailViewModel @Inject
constructor(gitRepository: GitRepository) : BaseViewModel() {

    val name = ObservableField<String>()
    val avatar = ObservableField<String>()
    val email = ObservableField<String>()
    val location = ObservableField<String>()
    val joinDate = ObservableField<String>()
    val followers = ObservableInt()
    val following = ObservableInt()
    val bio = ObservableField<String>()
    val repoList = MutableLiveData<List<Repo>>()

    private val gitRepository = gitRepository

    fun getUserDetail(login: String) {
        isLoading.set(true)
        compositeDisposable.add(
            gitRepository.fetchUserDetails(login)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ onUserDetailSuccess(it) }, { onError(it) }))

    }

    fun getUserRepo(login: String){
        isLoading.set(true)
        compositeDisposable.add(
            gitRepository.fetchUserRepo(login)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ onUserRepoSuccess(it) }, { onError(it) }))
    }

    private fun onUserDetailSuccess(response: UserDetailResponse){
        isLoading.set(false)
        name.set(response.name)
        avatar.set(response.avatar_url)
        email.set(response.email)
        location.set(response.location)
        joinDate.set(response.created_at)
        followers.set(response.followers)
        following.set(response.following)
        if (response.bio!=null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            bio.set(Html.fromHtml(response.bio,Html.FROM_HTML_MODE_LEGACY).toString())
        }else if(response.bio!=null){
            bio.set(Html.fromHtml(response.bio).toString())
        }
        getUserRepo(response.login)
    }

    private fun onUserRepoSuccess(response: List<Repo>){
        isLoading.set(false)
        repoList.value = response
    }

    private fun onError(throwable: Throwable){
        throwable.printStackTrace()
        isLoading.set(false)
        EventBus.publish(RxEvent.EventError(throwable))
    }
}